import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

// 초기 상태 정의
const initialState = {
    accessToken: null,
    isInitialized: false,
    user: null,
    isAuthenticated: false
};

// Async thunk: 로그인 요청
export const login = createAsyncThunk('auth/login', async ({ email, password }, thunkAPI) => {
    try {
        const response = await axios.post(
            '/user/login',
            { email, password },
            { withCredentials: true }
        );
        const accessToken = response.headers['authorization']?.replace('Bearer ', '');
        if (!accessToken) {
            throw new Error('No access token received');
        }
        return accessToken;
    } catch (error) {
        return thunkAPI.rejectWithValue('Login failed');
    }
});

// Async thunk: 초기화 요청
export const initializeAuth = createAsyncThunk('auth/initializeAuth', async (_, thunkAPI) => {
    try {
        const response = await axios.get('/user/refresh', { withCredentials: true });
        const newAccessToken = response.headers['authorization']?.replace('Bearer ', '');
        if (newAccessToken) {
            return newAccessToken;
        }
        return null;
    } catch (error) {
        return thunkAPI.rejectWithValue('Auth initialization failed');
    }
});

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        logout: (state) => {
            state.accessToken = null;
            state.user = null;
            state.isInitialized = false;
            state.isAuthenticated = false;
            delete axios.defaults.headers.common['Authorization'];
        },
        setAccessToken: (state, action) => {
            state.accessToken = action.payload;
            if (action.payload) {
                axios.defaults.headers.common['Authorization'] = `Bearer ${action.payload}`;
                const decoded = jwtDecode(action.payload);
                state.user = {
                    email: decoded.sub,
                    userName: decoded.userName,
                    profilePhoto: decoded.profilePhoto,
                    userId: decoded.userId,
                    userRole: decoded.auth
                };
                state.isInitialized = true;
                state.isAuthenticated = true;
            } else {
                delete axios.defaults.headers.common['Authorization'];
                state.user = null;
                state.isAuthenticated = false;
            }
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.fulfilled, (state, action) => {
                state.accessToken = action.payload;
                const decoded = jwtDecode(action.payload);
                state.user = {
                    email: decoded.sub,
                    userName: decoded.userName,
                    profilePhoto: decoded.profilePhoto,
                    userId: decoded.userId,
                    userRole: decoded.auth
                };
                state.isInitialized = true;
                state.isAuthenticated = true;
                axios.defaults.headers.common['Authorization'] = `Bearer ${action.payload}`;
            })
            .addCase(login.rejected, (state) => {
                state.accessToken = null;
                state.user = null;
                state.isInitialized = false;
                state.isAuthenticated = false;
            })
            .addCase(initializeAuth.fulfilled, (state, action) => {
                if (action.payload) {
                    state.accessToken = action.payload;
                    const decoded = jwtDecode(action.payload);
                    state.user = {
                        email: decoded.sub,
                        userName: decoded.userName,
                        profilePhoto: decoded.profilePhoto,
                        userId: decoded.userId,
                        userRole: decoded.auth
                    };
                    state.isInitialized = true;
                    state.isAuthenticated = true;
                    axios.defaults.headers.common['Authorization'] = `Bearer ${action.payload}`;
                }
            })
            .addCase(initializeAuth.rejected, (state) => {
                state.accessToken = null;
                state.user = null;
                state.isInitialized = false;
                state.isAuthenticated = false;
            });
    }
});

export const { logout, setAccessToken } = authSlice.actions;
export default authSlice.reducer;
