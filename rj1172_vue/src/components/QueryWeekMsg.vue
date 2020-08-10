<template>
    <div>
        <div>
            <el-form>
                <el-form-item>
                    <el-date-picker
                            v-model="order.orderdate"
                            align="right"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions"
                            value-format="yyyy-MM-dd"
                            >
                    </el-date-picker>
                    <el-select v-model="order.placename" placeholder="请选择" style="margin-left: 20px"
                               @change="getplacenumber">
                        <el-option
                                v-for="item in placenames"
                                :key="item"
                                :label="item"
                                :value="item">
                        </el-option>
                    </el-select>
                    <el-select v-model="order.placenumber" placeholder="请选择" style="margin-left: 20px">
                        <el-option
                                v-for="item in placeunmbers"
                                :key="item"
                                :label="item"
                                :value="item">
                        </el-option>
                    </el-select>
                    <el-button type="primary" @click="sure" style="margin-left: 20px">查询信息</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div>
            <el-table
                    :data="orders"
                    border
                    style="width: 100%">
                <el-table-column
                        prop="id"
                        label=""
                        width="50">
                </el-table-column>
                <el-table-column
                        prop="placename"
                        label="场地名称"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="placenumber"
                        label="场地号"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="orderdate"
                        label="预约日期"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="begintime"
                        label="开始时间"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="endtime"
                        label="结束时间"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="username"
                        label="预约者"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="userpost"
                        label="使用者职位"
                        width="120">
                </el-table-column>

            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="8"
                    :total="total"
                    @current-change="page"
                    style="text-align: center">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QueryWeekMsg",
        data(){
            return{
                placenames:[],
                placeunmbers:[],
                places:[],
                order:{
                  orderdate:'',
                  placename:'',
                  placenumber:'',
                },
                orders: [{
                    id:'1',
                    placename:'1',
                    placenumber:'1',
                    orderdate:'1',
                    begintime:'1',
                    endtime:'1',
                    username:'',
                    userpost:'',
                    status:'',
                    password:''
                }],
                pickerOptions: {
                    disabledDate(time) {
                        const date = new Date() ;
                        date.setTime(date.getTime() + 3600 * 1000 * 24 * 6);
                        return  time.getTime()<Date.now() - 8.64e7 || time.getTime()>date;
                    }
                },
                total:'',
            }
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/place/orders/1/8";
            axios.get(url1).then(function (resp) {
                _this.orders = resp.data.content;
                _this.total=resp.data.totalElements
            });
            const url = "http://localhost:8181/place/placenames";
            axios.get(url).then(function (resp) {
                _this.placenames = resp.data;
            });
            const url2 = "http://localhost:8181/place/places";
            axios.get(url2).then(function (resp) {
                _this.places = resp.data;
            });
        },
        methods:{
            getplacenumber(value){
                this.placeunmbers.length=0;
                this.placeunmbers.push("");
                for(let i=0;i< this.places.length;i++){
                    if(value === this.places[i].placename){
                        this.placeunmbers.push(this.places[i].placenumber);
                    }
                }
            },
            page(currentPage){
                if(this.order.orderdate === ''  &&
                    this.order.placenumber ===''){
                    const _this = this;
                    const url1 = "http://localhost:8181/place/orders/"+currentPage+"/8";
                    axios.get(url1).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements;
                    });
                }
                else if(this.order.orderdate !== '' && this.order.placename === '' &&
                    this.order.placenumber===''){
                    const _this = this;
                    const url1 = "http://localhost:8181/place/orderdateorders/"+currentPage+"/8";
                    axios.get(url1,{params:{orderdate:this.order.orderdate}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    });
                }
                else if(this.order.orderdate === '' && this.order.placename !== '' &&
                    this.order.placenumber!==''){
                    const _this = this;
                    const url1 = "http://localhost:8181/place/placeorders/"+currentPage+"/8";
                    axios.get(url1,{params:{placename:this.order.placename,
                            placenumber:this.order.placenumber}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
                else if(this.order.orderdate !== '' && this.order.placename !=='' &&
                    this.order.placenumber!==''){
                    const _this = this;
                    const url1 = "http://localhost:8181/place/placeanddateorders/"+currentPage+"/8";
                    axios.get(url1,{params:{placename:this.order.placename,placenumber:this.order.placenumber,
                            orderdate:this.order.orderdate}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
            },
            sure(){
                if(this.order.orderdate === '' &&
                    this.order.placenumber ===''){
                    this.orders.length=0;
                    this.total='';
                    const _this = this;
                    const url1 = "http://localhost:8181/place/orders/1/8";
                    axios.get(url1).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
                else if(this.order.orderdate !== '' && this.order.placename === '' &&
                    this.order.placenumber===''){
                    this.orders.length=0;
                    this.total='';
                    const _this = this;
                    const url1 = "http://localhost:8181/place/dateorders/1/8";
                    axios.get(url1,{params:{orderdate:this.order.orderdate}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
                else if(this.order.orderdate === '' && this.order.placename !== '' &&
                    this.order.placenumber!==''){
                    this.orders.length=0;
                    this.total='';
                    const _this = this;
                    const url1 = "http://localhost:8181/place/placeorders/1/8";
                    axios.get(url1,{params:{placename:this.order.placename,
                            placenumber:this.order.placenumber}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
                else if(this.order.orderdate !== '' && this.order.placename !== '' &&
                    this.order.placenumber!==''){
                    this.orders.length=0;
                    this.total='';
                    const _this = this;
                    const url1 = "http://localhost:8181/place/placeanddateorders/1/8";
                    axios.get(url1,{params:{placename:this.order.placename,placenumber:this.order.placenumber,
                            orderdate:this.order.orderdate}}).then(function (resp) {
                        _this.orders = resp.data.content;
                        _this.total=resp.data.totalElements
                    })
                }
            }
        }

    }
</script>

<style scoped>

</style>
