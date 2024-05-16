<template>
    <div id="join-view">
        <h2>회원가입</h2>
        <form id="join_form">
            <div class="input_wrap">
                <label for="id" class="inputHeader">ID</label>
                <div class="input_area">
                    <input type="text" name="id" id="id" :value="inputId" @input="onCheckId">
                    <div class="info" :class=" checkId ? 'checkInfo' : 'failInfo'">{{ checkId ? "사용 가능한 ID입니다." : "영어, 숫자만 포함하여 5글자 이상으로 설정해주세요."}}</div>
                </div>
                <div class="btn">중복확인</div>
            </div>
            <div class="input_wrap">
                <label for="password" class="inputHeader">PW</label>
                <div class="input_area">
                    <input type="password" name="password" id="password" :value="inputPW" @input="onCheckPW">
                    <div class="info" :class=" checkPW ? 'checkInfo' : 'failInfo'">{{ checkPW ? "사용 가능한 비밀번호입니다." : "영어, 숫자만 포함하여 5글자 이상으로 설정해주세요."}}</div>
                </div>
            </div>
            <div class="input_wrap">
                <label for="passwordCheck" class="inputHeader">PW확인</label>
                <div class="input_area">
                    <input type="password" name="confirmPW" id="confirmPW" :value="inputConfirmPW" @input="onCheckConfirmPW">
                    <div class="info" :class=" checkConfirmPW ? 'checkInfo' : 'failInfo'">{{ checkConfirmPW ? "비밀번호 확인되었습니다." : "비밀번호가 올바르지 않습니다."}}</div>
                </div>
            </div>
            <div class="input_wrap">
                <label for="name" class="inputHeader">이름</label>
                <div class="input_area">
                    <input type="text" name="name" id="name">
                </div>
            </div>
            <div class="input_wrap">
                <label for="nickname" class="inputHeader">닉네임</label>
                <div class="input_area">
                    <input type="text" name="nickname" id="nickname" :value="inputNickname" @input="onCheckNickname">
                    <div class="info checkInfo" :class=" checkNickname ? 'checkInfo' : 'failInfo'">{{ checkNickname ? "사용 가능한 닉네임입니다." : "닉네임을 입력해주세요."}}</div>
                </div>
                <div class="btn">중복확인</div>
            </div>
            <div class="input_wrap">
                <div class="inputHeader">성별</div>
                <div class="selectGender">
                    <input type="radio" name="gender" id="male" value="1" checked>
                    <label for="male">남자</label>
                </div>
                <div class="selectGender">
                    <input type="radio" name="gender" id="female" value="2">
                    <label for="female">여자</label>
                </div>
            </div>
            <div class="address_input_wrap">
                <div class="inputHeader">주소</div>
                <div class="searchAddress">
                    <input type="text" placeholder="주소">
                    <div class="btn">주소찾기</div>
                </div>
                <input type="text" placeholder="상세주소">
            </div>
            <button id="joinBtn">회원가입하기</button>
        </form>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const inputId = ref()
    const inputPW = ref()
    const inputConfirmPW = ref()
    const inputNickname = ref()

    const checkId = ref(false)
    const checkPW = ref(false)
    const checkConfirmPW = ref(false)
    const checkNickname = ref(false)

    const onCheckId = (event) => {
        const regex =/^[a-zA-Z0-9]*$/           // 영어 대소문자와 숫자만 가능
        inputId.value = event.target.value

        if(regex.test(inputId.value) && inputId.value.length >= 5) {
            checkId.value = true
        } else {
           checkId.value = false
        }
    }

    const onCheckPW = (event) => {
        const regex =/^[a-zA-Z0-9]*$/           // 영어 대소문자와 숫자만 가능
        inputPW.value = event.target.value

        if(regex.test(inputPW.value) && inputPW.value.length >= 5) {
            checkPW.value = true
        } else {
            checkPW.value = false
        }
    }

    const onCheckConfirmPW = (event) => {
        inputConfirmPW.value = event.target.value

        if(inputConfirmPW.value === inputPW.value) {
            checkConfirmPW.value = true 
        } else {
            checkConfirmPW.value = false
        }
    }
    
    const onCheckNickname = (event) => {
        inputNickname.value = event.target.value
        checkNickname.value = inputNickname.value ? true : false
    }
</script>

<style scoped>
    #join-view {
        width: 100vw;
        height: 95vh;
        display: flex;
        flex-direction: column;
        align-items: center ;
        margin-top: 25px;
    }

    #join-view h2 {
        font-family: 'Tenada';
        font-size: 2rem ;
    }
    
    #join_form {
        width: 80%;
        min-width: 800px;
        max-width: 1000px ;
        display:flex;
        flex-direction: column;
        justify-content: center;
        border: 1px solid var(--gray-color);
        box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
        padding: 5% 0 5% 6%;
        box-sizing: border-box;
    }

    .input_wrap {
        display: flex;
        font-family: 'NanumSquareRound';
        margin-bottom: 2%;
    }

    .inputHeader {
        width: 10%;
        font-size: 1.1rem ;
        font-weight: 600;
    }

    .input_area {
        width: 70%;
    }

    .input_area input {
        width: 90%;
        height: 25px ;
        border: 1px solid black;
        border-radius: 9px ;
        padding: 0 10px ;
        box-sizing: border-box ;
    }

    .info {
        font-size: 0.8rem;
        margin: 5px 0 0 5px ;
    }

    .checkInfo {
        color : #1D24CA ;
    }

    .failInfo {
        color: #E72929 ;
    }

    .address_input_wrap {
        display: flex ;
        flex-direction: column;
    }

    .searchAddress {
        display: flex ;
        margin: 10px 0 ;
    }

    .address_input_wrap input {
        width: 75%;
        height: 27px ;
        border: 1px solid black;
        border-radius: 9px ;
        padding: 0 10px ;
        box-sizing: border-box ;
    }

    .btn {
        width: 10%;
        height: 50% ;
        display: flex ;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
        font-weight: 600 ;
        font-size: 0.9rem ;
        background-color: var(--primary-color);
        font-family: 'NanumSquareRound';
    }

    .searchAddress .btn {
        height: 100% ;
        margin-left: 5% ;
    }

    #joinBtn {
        margin-top: 5% ;
        width: 25%;
        height: 40px ;
        margin-left: auto ;
        margin-right: auto;
        font-family: 'Tenada';
        font-size: 1.4rem ;
        background-color: var(--secondary-color);
        border: none;
        border-radius: 10px ;
        padding-top: 7px;
    }

    input[type='radio'] {
        --webkit-appearance: none; 
        --moz-appearance: none;
        appearance: none;
        width: 14px;
        height: 14px;
        border: 2px solid #ccc; 
        border-radius: 50%;
        outline: none; 
        cursor: pointer;
    }

    input[type='radio']:checked {
        background-color: var(--primary-color);
        border: 2px solid white; 
        box-shadow: 0 0 0 1.6px var(--primary-color);
    }

    .selectGender {
        margin-right: 5px ;
    }
</style>