<template>
    <div style="display: inline-block;width: 350px">
        <el-form label-width="100px">
            <el-form-item label="订单编号">
                <el-input v-model="Order.Orderid" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用器材名称">
                <el-input v-model="Order.Equipmentname" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用开始时间">
                <el-input v-model="Order.Renttime" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户ID">
                <el-input v-model="User.Userid"></el-input>
            </el-form-item>
            <el-form-item label="用户名称">
                <el-input v-model="User.Username"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="Confirm">确认归还</el-button>
                <el-button type="info" @click="Back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                Order:{
                    Orderid:'',
                    Equipmentname:'',
                    Userid:null,
                    Username: '',
                    Renttime: ''
                },
                User:{
                    Userid:null,
                    Username:''
                }
            }
        },
        methods:{
            Confirm() {
                if(this.User.Userid !== (this.Order.Userid + '')){
                    this.$alert('Id错误!','消息',{
                        confirmButtonText: '确定',
                    })
                }
                else if(this.User.Username !== this.Order.Username){
                    this.$alert('用户名错误!','消息',{
                        confirmButtonText: '确定',
                    })
                }
                else{
                    const _this = this;
                    const url1 = "http://localhost:8181/equipment/findStandardByName/";
                    const url2 = "http://localhost:8181/equipment/equipmentReturn/";
                    axios.get(url1 + this.Order.Equipmentname).then(function (resp) {
                        var renttime = Date.parse(new Date(_this.Order.Renttime));
                        var endttime = new Date().getTime();
                        var Price = (_this.diffTime(renttime,endttime) * resp.data.price).toFixed(2);
                        const imgsrc = require("../img/1.png");
                        axios.put(url2 + _this.$route.query.Id).then(function (resp) {
                            _this.$alert('<p style="text-align: center">请支付'+Price+'元</p><div class="alertclass"><img src=' + imgsrc + ' width=200px height: auto></div>','账单',{
                                confirmButtonText: '确定',
                                dangerouslyUseHTMLString: true,
                                callback:action => {
                                    _this.$alert('归还请求已发送,请等待审核','消息',{
                                        confirmButtonText: '确定',
                                        callback:action => {
                                            _this.$router.push("/equipmentreturn")
                                        }
                                    })
                                }
                            })
                        })
                    })

                }
            },
            Back(){
                this.$router.push("/equipmentreturn");
            },
            diffTime(startDate, endDate) {
                var diff = endDate - startDate;
                var days = Math.floor(diff / (24 * 3600 * 1000));
                var leave1 = diff % (24 * 3600 * 1000);
                var hours = Math.floor(leave1 / (3600 * 1000));
                var leave2 = leave1 % (3600 * 1000);
                var minutes = Math.floor(leave2 / (60 * 1000));
                return days * 24 + hours + minutes / 60;
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/equipment/getequipmentOrder/";
            axios.get(url + this.$route.query.Id).then(function (resp) {
                _this.Order = resp.data;
            })
        }
    }
</script>

<style>
.alertclass{
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>