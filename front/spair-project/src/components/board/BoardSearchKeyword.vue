<template>
    <div id="title"> 
    <div id="red">
        '{{ searchQuery }}' 
    </div>
    
    검색 결과입니다.
    </div>
    <BoardList/>
</template>

<script setup>
import BoardList from '@/components/board/BoardList.vue'
import { ref, watch, computed} from 'vue'
import { useRoute, useRouter }from 'vue-router'
import { useBoardStore } from '@/stores/board'

const store = useBoardStore()
const route = useRoute()
const searchQuery = ref(route.query.search)

// watch(() => route.query.search, async (newValue) => {
//   searchQuery.value = newValue || ''
//   await store.getBoardListSearch(searchQuery.value)
// })

// Computed 속성을 사용하여 검색어가 변경될 때마다 API를 호출
const searchResults = computed(async () => {
  try {
    if (searchQuery.value) {
      await store.getBoardListSearch(searchQuery.value)
      return store.boardListSearch 
    }
  } catch (error) {
    console.error('검색 결과를 가져오는 중 에러 발생:', error)
  }
})






</script>

<style scoped>
#title {
    font-size: 2rem;
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    margin: 2rem;
}
#red {
    color: red;
}
</style>