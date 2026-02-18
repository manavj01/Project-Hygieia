<template>
    <div class="page">
      <div class="card">
        <h1 class="title">{{ title }}</h1>
        <p v-if="subtitle" class="subtitle">{{ subtitle }}</p>
  
        <div class="content">
          <slot />
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  defineProps<{
    title: string;
    subtitle?: string;
  }>();
  </script>
  
  <style scoped>
  .page {
    min-height: 100vh;
    display: grid;
    place-items: center;
    padding: 24px;
  }
  .card {
    width: 100%;
    max-width: 420px;
    border: 1px solid #e5e7eb;
    border-radius: 14px;
    padding: 22px;
    background: white;
  }
  .title {
    margin: 0 0 6px 0;
    font-size: 22px;
    font-weight: 700;
  }
  .subtitle {
    margin: 0 0 16px 0;
    color: #6b7280;
    font-size: 14px;
  }
  </style>
  