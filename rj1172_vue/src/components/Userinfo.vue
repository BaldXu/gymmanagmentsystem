<template>
    <div>
        <el-table
                :data="users1"
                border
                style="width: 100%">
            <el-table-column
                    prop="sex"
                    label="用户ID"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="用户名"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="联系方式"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="联系地址"
                    width="300">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="reform(scope.row)" type="text" size="small">修改</el-button>

                </template>
            </el-table-column>
        </el-table>


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
        name: "Userinfo",
        data(){
            return{
                users:{
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
                        _this.$alert('预约修订成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload()
                    }
                    else{
                        _this.dialogFormVisible2 = false;
                        _this.$alert('预约修订失败!', '消息', {
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