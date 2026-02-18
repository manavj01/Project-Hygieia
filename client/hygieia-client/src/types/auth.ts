export type LoginRequest = {
    email: string;
    password: string;
  };
  
  export type SignupRequest = {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
  };
  
  export type AuthResponse = {
    // adjust to your backend response
    token?: string;
    user?: {
      id?: string | number;
      email: string;
      firstName?: string;
      lastName?: string;
    };
  };
  