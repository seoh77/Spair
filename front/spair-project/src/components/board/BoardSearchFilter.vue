<template>
    <div id="search-container">
        <h4>필터</h4>
        <div id="filter">

            <div class="wrap">
                <label for="status"></label>
                <!-- <select id="status" name="status" v-model="status"> -->
                <select id="status" name="status">
                    <option selected disabled>모집여부</option>
                    <option value='1'>모집중</option>
                    <option value="0">모집완료</option>
                </select>
            </div>
            <div class="wrap">
                <label for="status"></label>
                <!-- <select id="gender" name="gender" v-model="gender"> -->
                <select id="gender" name="gender">
                    <option selected disabled>성별</option>
                    <option value='1'>남자</option>
                    <option value="2">여자</option>
                    <option value="3">상관없음</option>
                </select>
            </div>
            <div class="wrap">
                <label for="exercise_type"></label>
                <!-- <select id="exercise-type" name="exercise_type" v-model="exerciseType"> -->
                <select id="exercise-type" name="exercise_type">
                    <option selected disabled>운동종류</option>
                    <option value="PT">PT</option>
                    <option value="필라테스">필라테스</option>
                    <option value="기타">기타</option>
                </select>
            </div>
            <div id="price-wrap">
                <div id="title">가격</div>
                <div id="min-max">
                    <div>
                        <label for="price">최저</label>
                        <!-- <input name="price" type="text" v-model="minPrice"/> -->
                        <input name="price" type="text" />
                    </div>
                    <div>
                        <label>최고</label>
                        <!-- <input name="price" type="text" v-model="maxPrice" /> -->
                        <input name="price" type="text" />
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
// 임시 메소드. fake 구현. API 연결 후 삭제 및 새롭게 생성 예정
import { ref, getCurrentInstance } from 'vue';

const status = ref('');
const gender = ref('');
const exerciseType = ref('');
const minPrice = ref('');
const maxPrice = ref('');

const instance = getCurrentInstance();

const applyFilters = () => {
    const filters = {
        status: status.value,
        gender: gender.value,
        exerciseType: exerciseType.value,
        minPrice: minPrice.value,
        maxPrice: maxPrice.value
    };

 if (instance) {
    instance.emit('apply-filters', filters);
    console.log("작동 되지 않는 fake 구현")
  }
};

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
}
select {
    width: 100%;
    height: 3rem;
    border-style: none;
    text-align: center;
    font-family: 'Tenada';
    font-size: 1.3rem;
    border-radius: 0.5rem;
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
}
#min-max {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 60%;
}
#min-max div {
    display: flex;
}
input {
    border-radius: 0.5rem;
    width: 70%;
    margin: 0 1rem;
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
}
</style>
  