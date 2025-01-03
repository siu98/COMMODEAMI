import { useSelector, useDispatch } from 'react-redux';
import { setAccessToken, logout } from '../store/slices/authSlice';

export const useAuth = () => {
    const dispatch = useDispatch();
    const { accessToken, user, isAuthenticated } = useSelector((state) => state.auth);

    // 로그인 액션
    const login = (token) => {
        dispatch(setAccessToken(token)); // 토큰 저장 및 상태 업데이트
    };

    // 로그아웃 액션
    const performLogout = () => {
        dispatch(logout());
    };

    return {
        isLoggedIn: isAuthenticated, // 로그인 상태
        user,            // 사용자 정보
        accessToken,     // 액세스 토큰
        login,           // 로그인 함수
        logout: performLogout, // 로그아웃 함수
    };
};
