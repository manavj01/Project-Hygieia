<template>
    <label class="field">
      <span class="label">{{ label }}</span>
      <input
        class="input"
        :type="type"
        :value="modelValue"
        @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)"
        :placeholder="placeholder"
        :autocomplete="autocomplete"
        :required="required"
      />
    </label>
  </template>
  
  <script setup lang="ts">
  defineProps<{
    label: string;
    type?: string;
    modelValue: string;
    placeholder?: string;
    autocomplete?: string;
    required?: boolean;
  }>();
  
  defineEmits<{
    (e: "update:modelValue", value: string): void;
  }>();
  </script>
  
  <style scoped>
  .field {
    display: grid;
    gap: 6px;
    margin-bottom: 12px;
  }
  .label {
    font-size: 13px;
    color: #374151;
  }
  .input {
    padding: 10px 12px;
    border-radius: 10px;
    border: 1px solid #d1d5db;
    outline: none;
  }
  .input:focus {
    border-color: #9ca3af;
  }
  </style>
  