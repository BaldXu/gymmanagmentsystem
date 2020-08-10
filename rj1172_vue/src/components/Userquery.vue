<template>
  <div> <el-form :label-position="labelPosition" label-width="100px" >
      <el-form-item label="输入用户ID">
          <el-input v-model="users.userid" type='string'style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
      </el-form-item>
  </el-form>
      <el-button type="primary" round @click.native="submit">确定</el-button>

      <el-dialog title="查询用户信息" :visible.sync="dialogFormVisible1" :show-close="true">
          <div>查询到以下信息</div>
          <div>用户ID：{{users1.userid}}</div>
          <div>用户名：{{users1.username}}</div>
          <div>性别：{{users1.sex}}</div>
          <div>联系方式：{{users1.phone}}</div>
          <div>联系地址：{{users1.address}}</div>
          <div slot="footer" class="dialog-footer">
              <el-button @click="reform" type="text" size="small">修改用户信息</el-button>
              <el-button type="primary" @click="deleteuser">删除该用户</el-button>
              <el-button plain @click="cancel">退出</el-button>
          </div>

      </el-dialog>
      <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible2" :show-close="true">
          <div>请输入要修改的项目</div>
          <div>
              <el-form :label-position="labelPosition" label-width="100px" >
                  <el-form-item label="用户名">
                      <el-input v-model="users.username"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
                  </el-form-item>
                  <el-form-item label="性别">
                      <el-input v-model="users.sex"style="width: 50px;padding-right: 10px;padding-left: 50px"></el-input>
                  </el-form-item>
                  <el-form-item label="联系方式">
                      <el-input v-model="users.phone"style="width: 300px;padding-right: 10px;padding-left: 50px"></el-input>
                  </el-form-item>
                  <el-form-item label="联系地址">
                      <el-input v-model="users.address"style="width: 600px;padding-right: 10px;padding-left: 50px"></el-input>
                  </el-form-item>
              </el-form>
          </div>


          <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="reformuser">确认</el-button>
              <el-button plain @click="cancel">取消</el-button>
          </div>
      </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "Userquery",
        data(){
            return{
                users:{
                    userid:'',
                    username:'',
                    password:'',
                    sex:'',
                    phone:'',
                    address:'',
                },
                dialogFormVisible1:false,
                dialogFormVisible2:false,
                users1:[{
                    userid:'',
                    username:'1',
                    password:'1',
                    sex:'1',
                    phone:'',
                    address:'',
                }],
            }
        },
        created() {
            const _this= this
            axios.get('http://localhost:8181/user/userinfo').then(function (resp) {
                console.log(resp.data)
                _this.gridData = resp.data
            })
        },
        methods:{
            reform(row) {
                this.dialogFormVisible2 = true;
                this.users.username=row.username;
                this.users.sex=row.sex;
                this.users.phone=row.phone;
                this.users.address=row.address;
            },
            cancel(){
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = false;
            },
            submit() {
                if (this.users.userid == '') {
                    this.$message.error('没有填写用户名！！')
                } else {
                    this.users.userid = '1';
                    this.dialogFormVisible1 = true;
                }
            },
            deleteuser(){
                const _this = this;
                const url = "http://localhost:8181/user/userinfo";
                axios.post(url, {id:this.order.id}).then(function (resp){
                    if(resp.data === "success"){
                        _this.dialogFormVisible1 = false;
                        _this.$alert('删除用户成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload()
                    }
                    else{
                        _this.dialogFormVisible1 = false;
                        _this.$alert('删除用户失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            },

                reformuser(){
                    const _this = this;
                    const url = "http://localhost:8181/user/userinfo";
                    axios.post(url,{username:this.users.username,
                        sex:this.users.sex,
                        phone:this.users.phone,
                        address:this.users.address,
                    }).then (resp=> {
                        if(resp.data === "success"){
                            _this.dialogFormVisible2 = false;
                            _this.$alert('修改信息成功!', '消息', {
                                confirmButtonText: '确定',
                            });
                            window.location.reload()
                        }
                        else{
                            _this.dialogFormVisible2 = false;
                            _this.$alert('修改信息失败!', '消息', {
                                confirmButtonText: '确定',
                            });
                        }
                    })
                }

        }
    }
</script>

<style scoped>

</style>