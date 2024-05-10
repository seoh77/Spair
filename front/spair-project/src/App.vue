
<template>
  초기 세팅
  <div>
    <TheHeaderNav/>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheHeaderNav from '@/components/common/TheHeaderNav.vue'
</script>

<style scoped>

</style>
