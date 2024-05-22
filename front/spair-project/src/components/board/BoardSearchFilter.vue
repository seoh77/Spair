<template>
    <div id="search-container">
        <h4>필터</h4>
        <div id="filter">

            <div class="wrap">
                <label for="status"></label>
                <select id="status" name="status" v-model="filter.status">
                    <option selected disabled value="">모집여부</option>
                    <option value='1'>모집중</option>
                    <option value="0">모집완료</option>
                </select>
            </div>
            <div class="wrap">
                <label for="status"></label>
                <select id="gender" name="gender" v-model="filter.gender">
                    <option selected disabled value="">성별</option>
                    <option value='1'>남자</option>
                    <option value="2">여자</option>
                    <option value="3">상관없음</option>
                </select>
            </div>
            <div class="wrap" >
                <label for="exercise_type" v-if="!typeselected"></label>
                <select id="exercise-type" name="exercise_type" v-model="filter.exerciseType" v-if="!typeselected" >
                    <option selected disabled value="">운동종류</option>
                    <option value="PT">PT</option>
                    <option value="필라테스">필라테스</option>
                    <option value="기타">기타</option>
                </select>
                <div v-else id="selected-type">
                    {{ exerciseType }}
                </div>
            </div>
            <div id="price-wrap">
                <div id="title">가격</div>
                <div id="min-max">
                    <div>
                        <label for="minPrice">최저</label>
                        <input name="minprice" type="text" v-model="filter.minPrice"/>
                    </div>
                    <div>
                        <label for="maxPrice">최고</label>
                        <input name="maxprice" type="text" v-model="filter.maxPrice" />
                    </div>
                </div>
            </div>
            <div id="btn">
                <button @click="applyFilters">적용</button>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const emit = defineEmits(['apply-filters'])

const filter = ref({
    status: '',
    gender: '',
    exerciseType: '',
    minPrice: '',
    maxPrice: '',
})

const typeselected= ref(false)
const exerciseType = route.query.exerciseType
if (route.query.exerciseType){
    typeselected.value = true
}else {
    typeselected.value = false
}

watch(() => route.query.exerciseType, (newExerciseType) => {
  if (newExerciseType) {
    typeselected.value = true
  } else {
    typeselected.value = false
  }
})




const applyFilters = function(){
    if (route.query.exerciseType === 'PT') { 
        // exerciseType가 PT인 경우에만 운동종류를 PT로 설정하고 emit으로 전달
        filter.value.exerciseType = 'PT'
        emit('apply-filters', { ...filter.value })
    } else if(route.query.exerciseType === '필라테스') {
        // exerciseType가 PT인 경우에만 운동종류를 PT로 설정하고 emit으로 전달
        filter.value.exerciseType = '필라테스';
        emit('apply-filters', { ...filter.value })
    }else {
        emit('apply-filters', { ...filter.value }) // exerciseType가 PT가 아닌 경우는 그대로 전달
    }
}

</script>
  
<style scoped>
#search-container {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-family: 'Tenada';
    margin: 0.5rem;
}

h4 {
    font-size: 1.6rem;
    width: 100%;
    display:flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    margin: 1rem;
}

#filter {
    display: flex;
    width: 100%;
    justify-content: space-between;
}

.wrap {
    width: 13%;
    border-radius: 0.5rem;
    box-shadow: 0.3rem 0.3rem 0.5rem gray;
    display: flex;
    justify-content: center;
    align-items: center;
}

.wrap:nth-child(1) {
    background-color: var(--primary-color);
}

.wrap:nth-child(2) {
    background-color: var(--sub-color);
}

.wrap:nth-child(3) {
    background-color: var(--secondary-color);
}

select {
    width: 100%;
    height: 3rem;
    border-style: none;
    text-align: center;
    font-family: 'Tenada';
    font-size: 1.3rem;
    border-radius: 0.5rem;
    padding: 0.4rem 0 0 0;
}

#selected-type {
    width: 100%;
    border-style: none;
    text-align: center;
    font-family: 'Tenada';
    font-size: 1.3rem;
    border-radius: 0.5rem;
    padding: 0.4rem 0 0 0;
}

#status {
    background-color: var(--primary-color);
}

#gender {
    background-color: var(--sub-color);
}

#exercise-type {
    background-color: var(--secondary-color);
}

#price-wrap {
    display: flex;
    width: 40%;
}

#title {
    width: 32%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: var(--gray-color);
    border-radius: 0.5rem;
    margin: 0 2rem 0 0;
    font-size: 1.3rem;
    box-shadow: 0.3rem 0.3rem 0.5rem gray;
    padding: 0.4rem 0 0 0;
}

#min-max {
    display: flex;
    flex-direction: column;
    justify-content: space-between ;
    width: 60%;
}

#min-max div {
    display: flex;
    align-items: center ;
}

#min-max div label {
    padding-top: 3px;
    font-size: 0.9rem;
}

#min {
    margin: 0 1rem 0.3rem 1rem;
}

input {
    border-radius: 0.5rem;
    width: 70%;
    height: 17px;
    margin: 0 1rem;
    padding-left: 7px ;
    font-size: 13px;
    border: 1px solid black ;
}

#btn {
    width: 10%;
}

button {
    background-color: #000000;
    width: 100%;
    height: 100%;
    border-radius: 0.8rem;
    font-size: 1.4rem;
    font-family: 'Tenada';
    color: #FFFFFF;
    box-shadow: 0.3rem 0.3rem 0.5rem gray;
    border: 1px solid black ;
    padding: 0.4rem 0 0 0;
}
</style>
  