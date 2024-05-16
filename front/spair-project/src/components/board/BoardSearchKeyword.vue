<template>
    <div id="title"> 
    <div id="red">
        '{{ searchQuery }}' 
    </div>
    
    결과입니다.
    </div>
    <BoardList/>
</template>

<script setup>
import BoardList from '@/components/board/BoardList.vue'
import { ref, watch} from 'vue'
import { useRoute }from 'vue-router'

const route = useRoute()
const searchQuery = ref(route.query.search)

watch(() => route.query.search, (newValue) => {
  searchQuery.value = newValue || ''
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