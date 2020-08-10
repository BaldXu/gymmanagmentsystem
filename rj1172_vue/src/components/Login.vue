<template>
    <el-dialog title="登录" :visible.sync="dialogFormVisible" :show-close="false">
        <el-form>
            <el-form-item label="用户名称" :label-width="formLabelWidth">
                <el-input v-model="user.username"></el-input>
            </el-form-item>
            <el-form-item label="用户密码" :label-width="formLabelWidth">
                <el-input v-model="user.password"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="login">登录</el-button>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        data(){
            return{
                user:{username:'',password:''},
                formLabelWidth:'120px',
                dialogFormVisible:true
            }
        },
        methods:{
            login(){
                const url = "http://localhost:8181/user/login";
                axios.get(url, {params:{username:this.user.username,password:this.user.password}})
                    .then(resp=> {
                        if(resp.data === "success"){
                            this.dialogFormVisible = false;
                            this.$message.success("登录成功!")
                            let user = JSON.stringify(this.user.username);
                            window.sessionStorage.setItem("user", user);
                            this.$router.push("/user")
                        }
                        else{
                            this.$message.error("用户名或密码错误!");
                        }
                });
            }
        }
    }
</script>

<style scoped>

</style>
