<template>
    <div style="margin-left: 20px">
        <div style="margin-bottom: 20px">
            <span>场地名称：</span>
            <el-select v-model="order.placename" placeholder="请选择" style="margin-left: 20px" @change="getplacenumber">
                <el-option
                        v-for="item in placenames"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
        </div>
        <div style="margin-bottom: 20px">
            <span>场 地 号 ：</span>
            <el-select v-model="order.placenumber" placeholder="请选择" style="margin-left: 20px">
                <el-option
                        v-for="item in placeunmbers"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
        </div>
        <div style="margin-bottom: 20px">
            <span>预约日期：</span>
            <el-date-picker
                    v-model="order.orderdate"
                    align="right"
                    type="date"
                    placeholder="选择日期"
                    :picker-options="pickerOptions"
                    value-format="yyyy-MM-dd"
                    style="margin-left: 20px">
            </el-date-picker>
        </div>
        <div style="margin-bottom: 20px">
            <span>预约时间：</span>
            <template>
                <el-time-select
                        placeholder="起始时间"
                        v-model="order.begintime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30'
                        }"
                        style="margin-left: 20px">
                </el-time-select>
                <el-time-select
                        placeholder="结束时间"
                        v-model="order.endtime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30',
                        minTime: order.begintime}"
                        style="margin-left: 20px">
                </el-time-select>
            </template>
        </div>

        <div style="margin-bottom: 20px">
            <span> 职 位：</span>
            <el-select v-model="order.userpost" placeholder="请选择" style="margin-left: 49px">
                <el-option
                        v-for="item in userposts"
                        :key="item.post"
                        :label="item.post"
                        :value="item.post">
                </el-option>
            </el-select>
        </div>

        <div style="margin-bottom: 20px">
            <el-button type="primary" @click="sure" style="margin-top: 40px">
                预约场地
            </el-button>
            <el-dialog title="系统消息" :visible.sync="dialogFormVisible1" :show-close="true">
                <span> 确认是否预约在{{order.placename}},{{order.placenumber}}场地</span>
                <span>   {{order.orderdate}} {{order.begintime}}~{{order.endtime}}</span>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="pay">确认</el-button>
                    <el-button plain @click="cancel">取消</el-button>
                </div>
            </el-dialog>
            <el-dialog title="付费" :visible.sync="dialogFormVisible2" :show-close="true">
                <el-input placeholder="请输入密码" v-model="order.password" show-password></el-input>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="orderplace">确认</el-button>
                    <el-button plain @click="cancel">取消</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PlaceOrder",
        data(){
            return{
                order:{
                    placename:'',
                    placenumber:'',
                    orderdate:'',
                    begintime:'',
                    endtime:'',
                    username:'',
                    userpost:'',
                    status:'',
                    password:''
                },
                user:{},
                placenames:[],
                placeunmbers:[],
                places:[],
                dialogFormVisible1:false,
                dialogFormVisible2:false,
                userposts:[
                    {post:'老师'},
                    {post:'社团'},
                    {post:'学生'}
                ],
                pickerOptions: {
                    disabledDate(time) {
                        const date = new Date() ;
                        date.setTime(date.getTime() + 3600 * 1000 * 24 * 6);
                        return  time.getTime()<Date.now() - 8.64e7 || time.getTime()>date;
                    }
                }
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/place/placenames";
            axios.get(url).then(function (resp) {
                _this.placenames = resp.data;
            });
            const url1 = "http://localhost:8181/place/places";
            axios.get(url1).then(function (resp) {
                _this.places = resp.data;
            });
            this.user=JSON.parse(sessionStorage.getItem("user"));
            this.order.username=this.user.username;
        },
        methods:{
            sure(){
                this.dialogFormVisible1 = true;
            },
            pay(){
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = true;
            },
            cancel(){
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = false;
            },
            getplacenumber(value){
                this.placeunmbers.length=0;
                for(let i=0;i< this.places.length;i++){
                    if(value === this.places[i].placename){
                        this.placeunmbers.push(this.places[i].placenumber)
                    }
                }
            },
            orderplace(){
                if(this.order.password === this.user.password){
                    const _this = this;
                    const url = "http://localhost:8181/place/placeorder";
                    axios.post(url,{placename:this.order.placename,
                        placenumber:this.order.placenumber,
                        orderdate:this.order.orderdate,
                        begintime:this.order.begintime,
                        endtime:this.order.endtime,
                        username:this.order.username,
                        userpost:this.order.userpost,
                        status:"已付费"}).then (resp=> {
                        if(resp.data === "success"){
                            _this.dialogFormVisible2 = false;
                            _this.$alert('预约成功!', '消息', {
                                confirmButtonText: '确定',
                            });
                        }
                        else{
                            _this.dialogFormVisible2 = false;
                            _this.$alert('预约失败!', '消息', {
                                confirmButtonText: '确定',
                            });
                        }
                    })
                }
                else {
                    alert("密码错误，请重新输入！")
                }

            }
        }
    }
</script>

<style scoped>

</style>
