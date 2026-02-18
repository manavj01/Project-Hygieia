import axios from "axios";

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: { "Content-Type": "application/json" },
});

// Later you can add interceptors here:
// api.interceptors.request.use((config) => { ... })
