<template>
    <AuthCard
      title="Login"
      subtitle="Use your email and password to access your account."
    >
      <form class="form" @submit.prevent="handleLogin">
        <FormField
          label="Email"
          type="email"
          v-model="email"
          placeholder="you@company.com"
          autocomplete="email"
          required
        />
        <FormField
          label="Password"
          type="password"
          v-model="password"
          placeholder="••••••••"
          autocomplete="current-password"
          required
        />
  
        <p v-if="error" class="error">{{ error }}</p>
  
        <button class="btn" type="submit" :disabled="loading">
          {{ loading ? "Logging in..." : "Login" }}
        </button>
  
        <div class="switch">
          <span>Don’t have an account?</span>
          <RouterLink class="link" to="/signup">Sign up</RouterLink>
        </div>
      </form>
    </AuthCard>
  </template>
  
  <script setup lang="ts">
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  import AuthCard from "@/components/auth/AuthCard.vue";
  import FormField from "@/components/auth/FormField.vue";
  import { login } from "@/services/auth.service";
  
  const router = useRouter();
  
  const email = ref("");
  const password = ref("");
  const loading = ref(false);
  const error = ref<string | null>(null);
  
  async function handleLogin() {
    error.value = null;
    loading.value = true;
  
    try {
      const res = await login({ email: email.value, password: password.value });
  
      // TODO: store token / user data here (Pinia or localStorage)
      // if (res.token) localStorage.setItem("token", res.token);
  
      // TODO: redirect to your app’s home/dashboard
      // router.push("/dashboard");
      alert("Login successful (wire redirect + token storage next)");
      console.log("login response:", res);
    } catch (e: any) {
      // If backend returns {message:"..."} you can extract it here
      error.value =
        e?.response?.data?.message ||
        e?.message ||
        "Login failed. Please try again.";
    } finally {
      loading.value = false;
    }
  }
  </script>
  
  <style scoped>
  .form {
    display: grid;
  }
  .btn {
    margin-top: 6px;
    padding: 10px 12px;
    border-radius: 10px;
    border: 1px solid #111827;
    background: #111827;
    color: white;
    cursor: pointer;
  }
  .btn:disabled {
    opacity: 0.7;
    cursor: not-allowed;
  }
  .switch {
    margin-top: 14px;
    display: flex;
    gap: 8px;
    justify-content: center;
    font-size: 14px;
    color: #6b7280;
  }
  .link {
    color: #111827;
    text-decoration: underline;
  }
  .error {
    margin: 6px 0 0 0;
    color: #b91c1c;
    font-size: 13px;
  }
  </style>
  