<template>
    <div id="update-container">
        <h4>게시글 수정</h4>
        <!--수정 가능 항목: 제목, 상태, 성별, 인원, 가격, 내용-->
        <!-- API 연결 후 v-model추가 및 수정 예정 -->
        <!--임시 데이터-->
        <div id="form-container">
            <div id="update">
                <div>
                    <label for="title">제목  </label>
                    <input type="text" id="title">
                </div>
                <div class="temporary">
                    <div class="label">작성자</div>
                    <div>테스터</div>
                </div>
                <div>
                    <label for="status">모집상태  </label>
                    <select name="status" id="status">
                        <option value="1">모집중</option>
                        <option value="0">모집완료</option>
                    </select>
                </div>
                <div>
                    <label for="gender">모집성별  </label>
                    <select name="gender" id="gender">
                        <option value="1">남성</option>
                        <option value="2">여성</option>
                        <option value="3">상관없음</option>
                    </select>
                </div>
                <div>
                    <label for="recruitment_num">모집인원  </label>
                    <select name="recruitment_num" id="recruitment_num">
                        <option value="1명">1명</option>
                        <option value="2명">2명</option>
                        <option value="3명 이상">3명 이상</option>
                        <option value="상관없음">상관없음</option>
                    </select>
                </div>
                <div class="temporary">
                    <div class="label">운동종류</div>
                    <div>테니스</div>
                </div>
                <div>
                    <label for="price">가격  </label>
                    <input type="price" id="price" placeholder="1인당 가격을 입력하세요.">
                    <span>원</span>
                </div>
                <div class="temporary">
                    <div class="label">주소 </div>
                    <div>고양시</div>
                </div>
                <div id="area">
                    <label for="content">내용  </label>
                    <textarea id="content" rows="10"></textarea>
                </div>
                <div id="up-btn">
                     <!-- API 연결 후 click이벤트 수정 예정-->
                    <button @click="golist">수정</button>
                </div>
                
            </div>
        </div>   
    </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
const store = useBoardStore();

const updateBoard = function () {
    store.updateBoard()
}

// 임시 변수
import router from '@/router'
const golist = function(){
    router.push({name: 'boardList'})
}
</script>

<style scoped>
#update-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
h4 {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    font-size: 1.5rem;
    margin: 1rem;
}
#form-container {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    border: 0.1rem solid var(--gray-color);
    box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
}
#update {
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: start;
    font-family: 'NanumSquareRound';
    font-size: 1rem;
    margin: 3rem;
}
#update div {
    width: 100%; 
    display: flex;
    margin-bottom: 0.6rem;
}
label {
    width: 10%;
    font-size: 1.2rem;
    font-weight: bold;
}
.temporary {
    display: flex;
    flex-direction: column;
    color: red;
}
.label {
    font-size: 1.2rem;
    font-weight: bold;
}
textarea {
    width: 100%;
    resize: none;
    overflow-y: scroll; 
    overflow-x: hidden;
    font-size: 1.2rem;
}
#area {
    display: flex;
    flex-direction: column;
}
input {
    width: 100%;
}
#up-btn {
        display: flex;
        flex-direction: row-reverse;
}
button {
    border-style: none;
    background-color: var(--secondary-color);
    font-size: 1.2rem;
    border-radius: 0.5rem;
    width: 7rem;
    height: 2.5rem;
    font-weight: bold;
}
</style>