<template>
    <div id="join-view">
        <h2>회원가입</h2>
        <form id="join_form">
            <div class="input_wrap">
                <label for="id" class="inputHeader">ID</label>
                <div class="input_area">
                    <input type="text" name="id" id="id" :value="inputId" @change="onCheckId" placeholder="영어, 숫자만 포함하여 5글자 이상으로 설정해주세요.">
                    <div class="info" :class=" checkId ? 'failInfo' : 'checkInfo' ">{{ loginIdInfo }}</div>
                </div>
                <div class="btn" @click="checkIdDuplicate">중복확인</div>
            </div>
            <div class="input_wrap">
                <label for="password" class="inputHeader">PW</label>
                <div class="input_area">
                    <input type="password" name="password" id="password" :value="inputPW" @change="onCheckPW" placeholder="영어, 숫자만 포함하여 5글자 이상으로 설정해주세요.">
                    <div class="info" :class="checkPW ? 'failInfo' : 'checkInfo'">{{ passwordInfo }}</div>
                </div>
            </div>
            <div class="input_wrap">
                <label for="passwordCheck" class="inputHeader">PW확인</label>
                <div class="input_area">
                    <input type="password" name="confirmPW" id="confirmPW" :value="inputConfirmPW" @change="onCheckConfirmPW">
                    <div class="info" :class="checkConfirmPW ? 'failInfo' : 'checkInfo'">{{ confirmPWInfo }}</div>
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
                <div class="btn" @click="checkNicknameDuplicate">중복확인</div>
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
                    <input type="text" placeholder="주소" :value="address" readonly>
                    <div class="btn" @click="searchAddress">주소찾기</div>
                </div>
                <input type="text" placeholder="상세주소" v-model="detailAddress">
            </div>
            <button id="joinBtn" :class=" allCheckValue ? 'possible' : 'impossible' " @click.prevent="joinClick">회원가입하기</button>
        </form>
    </div>
</template>

<script setup>
    import axios from 'axios';
    import { ref } from 'vue';

    // 회원가입 API 호출할 때 전송할 데이터 객체
    const joinData = {
        "loginId" : "",
        "password" : "",
        "nickname" : "",
        "gender" : 0,
        "address" : "",
        "latitude" : 0,
        "longitude" : 0
    }

    // 사용자가 입력한 값
    const inputId = ref()
    const inputPW = ref()
    const inputConfirmPW = ref()
    const inputNickname = ref()
    const inputGender = ref()
    const address = ref()
    const detailAddress = ref()

    // 사용자가 입력한 상태를 관리하기 위한 변수
    const checkId = ref(10)
    const checkPW = ref(10)
    const checkConfirmPW = ref(10)
    const checkNickname = ref(false)
    const allCheckValue = ref(false)

    // 안내문구 내용을 저장하는 변수
    const loginIdInfo = ref("")
    const passwordInfo = ref("")
    const confirmPWInfo = ref("")

    // 모든 조건을 충족하는지 확인하여 가입하기 버튼 활성화
    const allCheck = () => {
        if(!checkId.value || !checkPW.value || !checkConfirmPW.value || !checkNickname.value) {
            allCheckValue.value = false
        } else {
            allCheckValue.value = true
        }
    }

    // 입력한 loginId에 따라 안내문구 수정
    const changeloginIdInfo = () => {
        if(checkId.value === 0) {
            loginIdInfo.value = "사용 가능한 ID입니다."
        } else if (checkId.value === 1) {
            loginIdInfo.value = "이미 존재하는 ID입니다."
        } else if (checkId.value === 2) {
            loginIdInfo.value = "ID 중복 확인을 진행해주세요."
        } else if (checkId.value === 3) {
            loginIdInfo.value = "영어, 숫자만 포함하여 5글자 이상으로 설정해주세요."
        }
    }

    // 입력한 loginId가 조건을 충족하는지 확인
    const onCheckId = (event) => {
        const regex =/^[a-zA-Z0-9]*$/           // 영어 대소문자와 숫자만 가능
        inputId.value = event.target.value

        if(regex.test(inputId.value) && inputId.value.length >= 5) {
            checkId.value = 2
        } else {
           checkId.value = 3
        }

        changeloginIdInfo() 
        allCheck()
    }

    // 입력한 loginId가 기존 회원의 loginId와 중복되는지 확인
    const checkIdDuplicate = () => {
        // 조건에 충족하는지 먼저 검사
        if(checkId.value > 3) {
            onCheckId() 
        }

        // 조건을 충족하지 못했다면 바로 종료
        if(checkId.value >= 3) return ;

        axios.get(
            `http://localhost:8080/api/check/id/${inputId.value}`
        ).then((response) => {
            const result = response.data
            alert(result)

            if(result === "이미 존재하는 ID입니다.") {
                checkId.value = 1
                inputId.value = ""
            } else {
                checkId.value = 0
            }

            changeloginIdInfo() 
        }).catch((error) => {
            console.error(error)
        })
    }

    // 입력한 password에 따라 안내문구 수정
    const changePasswordInfo = () => {
        if(checkPW.value === 0) {
            passwordInfo.value = "사용 가능한 비밀번호입니다."
        } else if (checkPW.value === 1) {
            passwordInfo.value = "영어, 숫자만 포함하여 5글자 이상으로 설정해주세요."
        }
    }

    // 입력한 password가 조건을 충족하는지 확인
    const onCheckPW = (event) => {
        const regex =/^[a-zA-Z0-9]*$/           // 영어 대소문자와 숫자만 가능
        inputPW.value = event.target.value

        if(regex.test(inputPW.value) && inputPW.value.length >= 5) {
            checkPW.value = 0
        } else {
            checkPW.value = 1
        }

        changePasswordInfo()
        allCheck()
    }

    // 입력한 PW 확인 값에 따라 안내문구 수정
    const changeConfirmPWInfo = () => {
        if(checkConfirmPW.value === 0) {
            confirmPWInfo.value = "비밀번호가 일치합니다."
        } else if (checkConfirmPW.value === 1) {
            confirmPWInfo.value = "비밀번호가 일치하지 않습니다."
        }
    }

    // 입력한 PW 확인 값이 password와 일치하는지 확인
    const onCheckConfirmPW = (event) => {
        inputConfirmPW.value = event.target.value

        if(inputConfirmPW.value === inputPW.value) {
            checkConfirmPW.value = 0
        } else {
            checkConfirmPW.value = 1
        }

        changeConfirmPWInfo()
        allCheck()
    }
    
    // 입력한 nickname이 조건을 충족하는지 확인
    const onCheckNickname = (event) => {
        inputNickname.value = event.target.value
        checkNickname.value = inputNickname.value ? true : false
        allCheck()
    }

    // 입력한 nickname이 기존 회원의 nickname과 중복되는지 확인
    const checkNicknameDuplicate = () => {
        axios.get(
            `http://localhost:8080/api/check/nickname/${inputNickname.value}`
        ).then((response) => {
            const result = response.data
            alert(result)

            if(result === "이미 존재하는 닉네임입니다.") {
                checkNickname.value = false
                inputNickname.value = ""
            }
        }).catch((error) => {
            console.error(error)
        })
    }

    // 사용자의 주소를 입력
    const searchAddress = () => {
        new daum.Postcode({
            oncomplete: function(data) {
                address.value = data.address
            }
        }).open()
    }

    // 사용자가 입력한 주소로부터 위도, 경도 값을 계산
    const getCoordinate = async() => {
        await axios({
            url : 'https://dapi.kakao.com/v2/local/search/address.json?query=' + encodeURI(address.value),
            method : 'GET',
            headers : {
                Authorization : `KakaoAK ${import.meta.env.VITE_KAKAO_REST_API_KEY}`
            }
        }).then((response) => {
            const result = response.data.documents[0]
            joinData.latitude = result.y
            joinData.longitude = result.x
        })
    }

    // 회원가입 버튼 클릭
    const joinClick = () => {
        getCoordinate()
        console.log(joinData)
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
        font-family: 'NanumSquareRound';
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
        border: none;
        border-radius: 10px ;
        padding-top: 7px;
    }

    .possible {
        background-color: var(--secondary-color);
    }

    .impossible {
        background-color: var(--gray-color);
        color: white;
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