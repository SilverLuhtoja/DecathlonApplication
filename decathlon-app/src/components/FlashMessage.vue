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
  background: rgba(255, 0, 0, 0.753);
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

@media (max-width: 1024px) {
  .flash-message {
    position:fixed;
    top: 50%;
    width: 20rem;
  }
}
</style>