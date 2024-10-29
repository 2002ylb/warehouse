<template>
  <div class="register-container">
    <h1>注册</h1>
    <form @submit.prevent="register">
       
      <div class="form-group">
        <label for="no">用户名:</label>
        <input type="text" id="no" v-model="user.no" required>
      </div>
     
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="user.password" required>
      </div>
      <div class="form-group">
        <label for="confirm-password">确认密码:</label>
        <input type="password" id="confirm-password" v-model="user.confirmPassword" required>
      </div>
      <div class="form-group">
        <button type="submit" @click="register">注册</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
       
        no: '',
       
        password: '',
         confirmPassword: ''
      }
    };
  },
  computed: {
   
  },
  methods: {
    register() {
      // 在这里添加注册逻辑，例如发送请求到后端API
      

            this.$axios.post(this.$httpUrl+'/user/register',this.user).then(res=>res.data).then(res=>{
                            console.log(res)
                            if(!res){
                                //存储
                                sessionStorage.setItem("CurUser",JSON.stringify(res.user))

                                
                               
                                //跳转到主页
                                this.$router.replace('/');
                            }else{
                                
                                alert('校验失败，用户名或密码错误！');
                                this.$router.push('/register');
                            }
                        });


     
      
    }
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
}
</style>
