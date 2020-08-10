<template>
    <div style="display: inline-block">
        <el-form label-width="100px">
            <el-form-item label="租用器材编号">
                <el-input v-model="EquipmentOrder.EquipmentId" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用器材名称">
                <el-input v-model="EquipmentOrder.EquipmentName" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户ID">
                <el-input v-model="EquipmentOrder.UserId"></el-input>
            </el-form-item>
            <el-form-item label="用户名称">
                <el-input v-model="EquipmentOrder.UserName"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="Rent">申请</el-button>
                <el-button type="info" @click="Back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                EquipmentOrder:{
                    EquipmentId:'',
                    EquipmentName:'',
                    UserId:'',
                    UserName:''
                },
                id:null,
                username:''
            }
        },
        methods:{
            Back(){
                this.$router.push("/equipmentquery");
            },
            Rent(){
                const _this = this;
                const url = "http://localhost:8181/equipment/postOrder/"
                if(this.EquipmentOrder.UserId !== this.id + ""){
                    this.$alert('用户Id错误!','消息',{
                        confirmButtonText:"确定",
                    })
                }
                else if(this.EquipmentOrder.UserName !== this.username){
                    this.$alert('用户名称错误!','消息',{
                        confirmButtonText:"确定",
                    })
                }
                else{
                    axios.post(url , {EquipmentId:this.$route.query.id,UserId:this.EquipmentOrder.UserId}).then(function (resp) {
                        _this.$alert('租用请求发送成功!请等待审核','消息',{
                            confirmButtonText:"确定",
                            callback: action => {
                                _this.$router.push("/equipmentquery");
                            }
                        })
                    })
                }
            }
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/equipment/findById/";
            let username = window.sessionStorage.getItem("user").substring(1,window.sessionStorage.getItem("user").length-1);
            const url2 = "http://localhost:8181/equipment/findUserByName/";
            axios.get(url1 + this.$route.query.id).then(function (resp) {
                _this.EquipmentOrder.EquipmentId = resp.data.id;
                _this.EquipmentOrder.EquipmentName = resp.data.name;
            });
            axios.get(url2 + username).then(function (resp) {
                _this.id = resp.data;
                _this.username = username;
            });
        }
    }
</script>

<style scoped>

</style>
