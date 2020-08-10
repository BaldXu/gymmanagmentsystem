<template>
 <div>
     <el-form :label-position="labelPosition" label-width="100px" >
         <el-form-item label="输入当前密码">
             <el-input v-model="password1" type='string'style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
         </el-form-item>
     </el-form>
     <el-button type="primary" round @click.native="submit">确定</el-button>


     <el-dialog title="新密码" :visible.sync="dialogTableVisible1" width="1500px">
         <el-form :label-position="labelPosition" label-width="100px" >
             <el-form-item label="请输入新密码">
                 <el-input v-model="password"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
             </el-form-item>
             <el-form-item label="再次输入新密码">
                 <el-input v-model="password"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
             </el-form-item>
         </el-form>

         <div slot="footer" class="dialog-footer">
             <el-button type="primary" @click="reformpass">确认</el-button>
             <el-button plain @click="cancel">取消</el-button>
         </div>
     </el-dialog>
 </div>
</template>

<script>
    export default {
        name: "Usersecurity",
        data() {
            return {
                labelPosition: 'right',
                password1: '123',
                password:'',
                dialogTableVisible1: false,
            }
            },
        created() {
            const _this= this
            axios.get('http://localhost:8181/user/userinfo').then(function (resp) {
                console.log(resp.data)
                _this.gridData = resp.data
            })
        },
                methods:
            {
                submit()
                {
                    if (this.password1 == '') {
                        this.$message.error('没有填写密码！！')
                    } else {
                        this.password1 = '123';
                        this.dialogTableVisible1 = true;
                    }
                },
                cancel(){
                    this. dialogTableVisible1=false;

                },
                reformpass(){
                    const _this = this;
                    const url = "http://localhost:8181/user/userinfo";
                    axios.post(url,{password:this.password,
                    }).then (resp=> {
                        if(resp.data === "success"){
                            _this.dialogFormVisible2 = false;
                            _this.$alert('修改成功!', '消息', {
                                confirmButtonText: '确定',
                            });
                            window.location.reload()
                        }
                        else{
                            _this.dialogFormVisible2 = false;
                            _this.$alert('修改失败!', '消息', {
                                confirmButtonText: '确定',
                            });
                        }
                    })
                }
            },

        }
</script>

<style scoped>

</style>