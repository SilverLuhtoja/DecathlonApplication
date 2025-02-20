<script setup lang="ts">
const props = defineProps<{
  message: String
}>()
</script>

<template>
  <transition name="fade">
    <div class="flash-message">{{ props.message }}</div>
  </transition>
</template>


<style scoped>
.flash-message {
  background: red;
  color: white;
  padding: 10px;
  border-radius: 5px;
}

/* Vue Transition */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>