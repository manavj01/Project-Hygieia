import { api } from "./api";
import type { LoginRequest, SignupRequest, AuthResponse } from "@/types/auth";

// Update these paths to match your Spring Boot endpoints
const LOGIN_PATH = "users/login";
const SIGNUP_PATH = "users/signup";

export async function login(payload: LoginRequest): Promise<AuthResponse> {
  const { data } = await api.post<AuthResponse>(LOGIN_PATH, payload);
  return data;
}

export async function signup(payload: SignupRequest): Promise<AuthResponse> {
  const { data } = await api.post<AuthResponse>(SIGNUP_PATH, payload);
  return data;
}
