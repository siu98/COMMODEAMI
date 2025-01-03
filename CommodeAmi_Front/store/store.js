import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counterSlice';
import authReducer from '../store/slices/authSlice';

const store = configureStore({
    reducer: {
        // 여기에 리듀서 추가 
        auth: authReducer,
        counter: counterReducer,
    }
});

export default store;