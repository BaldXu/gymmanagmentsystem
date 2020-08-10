<template>
    <div>
        <el-table
                :data="orders"
                border
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="预约信息"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="placename"
                    label="场地名称"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="placenumber"
                    label="场地号"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="orderdate"
                    label="预约日期"
                    width="300">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="reform(scope.row)" type="text" size="small">修订</el-button>
                    <el-button @click="handleClick(scope.row)" type="text" size="small">退订</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                page-size="6"
                :total="total"
                @current-change="page"
                style="text-align: center">
        </el-pagination>

        <el-dialog title="退订场地消息" :visible.sync="dialogFormVisible1" :show-close="true">
            <div>是否退订以下场地</div>
            <div>预约编号：{{order.id}}</div>
            <div>场地名称：{{order.placename}}</div>
            <div>场 地 号 ：{{order.placenumber}}</div>
            <div>预约日期：{{order.orderdate}}</div>
            <div>开始时间：{{order.begintime}}</div>
            <div>结束时间：{{order.endtime}}</div>
            <div>职    位：{{order.userpost}}</div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="deleteorder">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修订订场地消息" :visible.sync="dialogFormVisible2" :show-close="true">
            <div>请输入要修订的场地</div>
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
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="reformorder">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "PlaceOrderMsg",
        data(){
            return{
                order:{
                    id:'',
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
                dialogFormVisible1:false,
                dialogFormVisible2:false,
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
                user:{},
                total:'',
                placenames:[],
                placeunmbers:[],
                places:[],
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
        created(){
            this.user=JSON.parse(sessionStorage.getItem("user"));
            const _this = this;
            const url1 = "http://localhost:8181/place/userorders/1/6";
            axios.get(url1,{params:{username:this.user.username}}).then(function (resp) {
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
            })

        },
        methods:{
            getplacenumber(value){
                this.placeunmbers.length=0;
                for(let i=0;i< this.places.length;i++){
                    if(value === this.places[i].placename){
                        this.placeunmbers.push(this.places[i].placenumber)
                    }
                }
            },
            page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/userorders/"+currentPage+"/6";
                axios.get(url,{params:{username:this.user.username}}).then(function (resp) {
                    _this.orders = resp.data.content;
                    _this.total=resp.data.totalElements
                })
            },
            cancel(){
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = false;
            },
            handleClick(row) {
                this.dialogFormVisible1 = true;
                this.order.id=row.id;
                this.order.placename=row.placename;
                this.order.placenumber=row.placenumber;
                this.order.orderdate=row.orderdate;
                this.order.begintime=row.begintime;
                this.order.endtime=row.endtime;
                this.order.userpost=row.userpost
            },
            reform(row) {
                this.dialogFormVisible2 = true;
                this.order.id=row.id;
                this.order.placename=row.placename;
                this.order.placenumber=row.placenumber;
                this.order.orderdate=row.orderdate;
                this.order.begintime=row.begintime;
                this.order.endtime=row.endtime;
                this.order.userpost=row.userpost;
                for(let i=0;i< this.places.length;i++){
                    if(row.placename === this.places[i].placename){
                        this.placeunmbers.push(this.places[i].placenumber)
                    }
                }
            },
            deleteorder(){
                const _this = this;
                const url = "http://localhost:8181/place/orderdelete";
                axios.post(url, {id:this.order.id}).then(function (resp){
                    if(resp.data === "success"){
                        _this.dialogFormVisible1 = false;
                        _this.$alert('预约退订成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload()
                    }
                    else{
                        _this.dialogFormVisible1 = false;
                        _this.$alert('预约退订失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            },
            reformorder(){
                const _this = this;
                const url = "http://localhost:8181/place/reformorder";
                axios.post(url,{id:this.order.id,
                    placename:this.order.placename,
                    placenumber:this.order.placenumber,
                    orderdate:this.order.orderdate,
                    begintime:this.order.begintime,
                    endtime:this.order.endtime,
                    userpost:this.order.userpost,
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
