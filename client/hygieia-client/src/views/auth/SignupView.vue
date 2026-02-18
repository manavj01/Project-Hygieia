<template>
    <AuthCard
      title="Sign up"
      subtitle="Create your account to get started."
    >
      <form class="form" @submit.prevent="handleSignup">
        <div class="row">
          <FormField
            label="First name"
            v-model="firstName"
            placeholder="John"
            autocomplete="given-name"
            required
          />
          <FormField
            label="Last name"
            v-model="lastName"
            placeholder="doe"
            autocomplete="family-name"
            required
          />
        </div>
  
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
          autocomplete="new-password"
          required
        />
  
        <p v-if="error" class="error">{{ error }}</p>
  
        <button class="btn" type="submit" :disabled="loading">
          {{ loading ? "Creating..." : "Create account" }}
        </button>
  
        <div class="switch">
          <span>Already have an account?</span>
          <RouterLink class="link" to="/login">Login</RouterLink>
        </div>
      </form>
    </AuthCard>
  </template>
  
  <script setup lang="ts">
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  import AuthCard from "@/components/auth/AuthCard.vue";
  import FormField from "@/components/auth/FormField.vue";
  import { signup } from "@/services/auth.service";
  
  const router = useRouter();
  
  const firstName = ref("");
  const lastName = ref("");
  const email = ref("");
  const password = ref("");
  
  const loading = ref(false);
  const error = ref<string | null>(null);
  
  async function handleSignup() {
    error.value = null;
    loading.value = true;
  
    try {
      const res = await signup({
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value,
        password: password.value,
      });
  
      // Optional: auto-login behavior depends on backend response
      alert("Signup successful. Please login.");
      console.log("signup response:", res);
  
      router.push("/login");
    } catch (e: any) {
      error.value =
        e?.response?.data?.message ||
        e?.message ||
        "Signup failed. Please try again.";
    } finally {
      loading.value = false;
    }
  }
  </script>
  
  <style scoped>
  .form {
    display: grid;
  }
  .row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
  }
  @media (max-width: 440px) {
    .row {
      grid-template-columns: 1fr;
    }
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
  