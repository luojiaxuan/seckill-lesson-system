<template>
  <portal-template>
    <div id="login">
      <el-form label-position="top" label-width="80px" :model="user"
       :rules="rules" ref="ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="请输用户名  "></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="user.password" placeholder="请输密码  "></el-input>
        </el-form-item>
        <el-button class="submit-btn" type="primary" @click="login('ruleForm')">登录</el-button>
      </el-form>
      <router-link to="/register"><el-button type="text" icon="el-icon-edit">去注册页</el-button></router-link>
    </div>
  </portal-template>
</template>

<script>
import PortalTemplate from './PortalTemplate'
export default {
  name: 'login',
  components: {
    PortalTemplate
  },
  data () {
    return {
      user: {
        username: '',
        password: ''
      },
      msg: 'Welcome to Your Vue.js App',
      rules: {
        username: [
           { required: true, message: '请输入用户名', trigger: 'blurs' }
         ],
         password: [
          { required: true, message: '请输入密码 ', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login(formName) {
      var self = this;
      self.$refs[formName].validate((valid) => {
         if (valid) {
          //  self.axios.post('/api/login',qs.stringify({username:self.user.username,password: self.user.password})).then(function (response){
          //    alert("success"+response);
          //  }).catch(function (error){
          //    alert("error"+ error)
          //  })

           self.$store.dispatch('login', {username: self.user.username, password: self.user.password})
           .then((response) => {
             self.$message.success(response.data.message)
             self.$router.push('/home/course/list');
           })
           .catch((response) => {
             self.$message.error(response.data.message)
           })
       }
       });
    }
  }
}
</script>

<style lang="scss">
</style>
