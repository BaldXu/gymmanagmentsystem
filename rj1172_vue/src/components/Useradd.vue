<template>
    <div style="margin-left: 20px">
        <el-form :label-position="labelPosition" label-width="100px" >
            <el-form-item label="用户ID">
                <el-input v-model="add.userid" type='number'style="width: 100px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="add.username"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="add.password"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-input v-model="add.sex"style="width: 50px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
            <el-form-item label="联系方式">
                <el-input v-model="add.phone"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
            <el-form-item label="联系地址">
                <el-input v-model="add.address"style="width: 600px;padding-right: 10px;padding-left: 50px"></el-input>
            </el-form-item>
        </el-form>


        <div style="margin-bottom: 20px">
            <el-button type="primary" round @click.native="submit">确定</el-button>
            <el-button type="info" round @click.native="reset">重置</el-button>

        </div>
    </div>
</template>

<script>
    export default {
        name: "Useradd",
        data(){
            return{
                add:{
                    userid:'',
                    username:'',
                    password:'',
                    sex:'',
                    phone:'',
                    address:'',
                },


            }
        },
        methods: {
            submit(){
                const _this  = this;
                axios.post('http://localhost:8181/user/userinfo',this.add).then(function (resp) {
                    if(resp.data == 'success'){
                        _this.$message({
                            message: '添加成功！！',
                            type: 'success'
                        });
                    }else {
                        _this.$message.error('未知原因添加失败');
                    }
                })

            },

            reset(){
                this.add.userid='';
                this.add.username='';
                this.add.password='';
                this.add.sex='';
                this.add.phone='';
                this.add.address='';
                this.$message({
                    message: '重置成功！！',
                    type: 'success'
                });
            }
        },
        created() {
            const _this= this
            axios.get('http://localhost:8181/user/userinfo').then(function (resp) {
                console.log(resp.data)
                _this.gridData = resp.data
            })
        }
    }
</script>

<style scoped>

</style>