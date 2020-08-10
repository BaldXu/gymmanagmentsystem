<template>
    <div>
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
                        width="180" >
                </el-table-column>
                <el-table-column
                        prop="placenumber"
                        label="场地号"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="orderdate"
                        label="使用日期"
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
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">退款</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="4"
                    :total="total"
                    @current-change="page"
                    style="text-align: center">
            </el-pagination>
            <el-dialog title="系统消息" :visible.sync="dialogFormVisible" :show-close="true">
                <span> 确认是否申请退款！！是，请写明退款原因</span>
                <div>
                    <el-input v-model="order.reason" placeholder="请输入退款原因"
                              style="width: 250px;padding-right: 10px;"></el-input>
                </div>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="refund">确认</el-button>
                    <el-button plain @click="cancel">取消</el-button>
                </div>
            </el-dialog>
        </div>

        <div style="margin-top: 10px">
            <el-table
                    :data="refundorders"
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
                        width="180" >
                </el-table-column>
                <el-table-column
                        prop="placenumber"
                        label="场地号"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="orderdate"
                        label="使用日期"
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
                        prop="status"
                        label="状态"
                        width="130">
                </el-table-column>
            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="3"
                    :total="total1"
                    @current-change="page1"
                    style="text-align: center">
            </el-pagination>
        </div>

        <div>
            <el-table
                    :data="surerefunds"
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
                        width="180" >
                </el-table-column>
                <el-table-column
                        prop="placenumber"
                        label="场地号"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="orderdate"
                        label="使用日期"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="begintime"
                        label="开始时间"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="endtime"
                        label="结束时间"
                        width="130">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button @click="click(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="agree(scope.row)" type="text" size="small">同意</el-button>
                        <el-button @click="disagree(scope.row)" type="text" size="small">不同意</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="3"
                    :total="total2"
                    @current-change="page2"
                    style="text-align: center">
            </el-pagination>
            <el-dialog title="退款原因" :visible.sync="dialogFormVisible1" :show-close="true">
                {{order.reason}}
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="cancel">确认</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                order:{
                    id:'',
                    reason:'',
                },
                refundorders:[],
                surerefunds:[],
                orders:[],
                total:'',
                total1:'',
                total2:'',
                dialogFormVisible:false,
                dialogFormVisible1:false,
                user:{},
            }
        },
        created() {
            this.user=JSON.parse(sessionStorage.getItem("user"));
            const _this = this;
            const url1 = "http://localhost:8181/place/payorders/1/4";
            axios.get(url1,{params:{status:"已付费",username:this.user.username}}).then(function (resp) {
                _this. orders = resp.data.content;
                _this.total=resp.data.totalElements
            });
            const url = "http://localhost:8181/place/refunds/1/3";
            axios.get(url,{params:{status:"已付费",username:this.user.username}}).then(function (resp) {
                _this.refundorders = resp.data.content;
                _this.total1=resp.data.totalElements
            });
            const url2 = "http://localhost:8181/place/refundorders/1/3";
            axios.get(url2,{params:{status:"申请中"}}).then(function (resp) {
                _this.surerefunds = resp.data.content;
                _this.total2=resp.data.totalElements
            });
        },
        methods:{
            page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/payorders/"+currentPage+"/4";
                axios.get(url,{params:{status:"已付费",username:this.user.username}}).then(function (resp) {
                    _this.orders = resp.data.content;
                    _this.total=resp.data.totalElements
                })
            },
            page1(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/refunds/"+currentPage+"/3";
                axios.get(url,{params:{status:"已付费",username:this.user.username}}).then(function (resp) {
                    _this.refundorders = resp.data.content;
                    _this.total1=resp.data.totalElements
                })
            },
            page2(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/refundorders/"+currentPage+"/3";
                axios.get(url,{params:{status:"申请中"}}).then(function (resp) {
                    _this.surerefunds = resp.data.content;
                    _this.total2=resp.data.totalElements
                })
            },
            cancel(){
                this.dialogFormVisible = false;
                this.dialogFormVisible1 = false;
            },
            handleClick(row) {
                this.dialogFormVisible = true;
                this.order.id=row.id;
            },
            click(row){
                this.dialogFormVisible1 = true;
                this.order.reason=row.reason;
                this.order.id=row.id;
            },
            agree(row){
                this.order.id=row.id;
                const _this = this;
                const url = "http://localhost:8181/place/refund";
                axios.post(url, {id:this.order.id,status:"已退款"}).then(function (resp){
                    if(resp.data === "success"){
                        _this.dialogFormVisible = false;
                        _this.$alert( '同意成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload()
                    }
                    else{
                        _this.dialogFormVisible = false;
                        _this.$alert('同意失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            },
            disagree(row){
                this.order.id=row.id;
                const _this = this;
                const url = "http://localhost:8181/place/refund";
                axios.post(url, {id:this.order.id,status:"不同意"}).then(function (resp){
                    if(resp.data === "success"){
                        _this.dialogFormVisible = false;
                        _this.$alert( '不同意成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload();
                    }
                    else{
                        _this.dialogFormVisible = false;
                        _this.$alert('不同意失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            },
            refund(){
                const _this = this;
                    const url = "http://localhost:8181/place/applyrefund";
                axios.post(url, {id:this.order.id,reason:this.order.reason,status:"申请中"}).then(function (resp){
                    if(resp.data === "success"){
                        _this.dialogFormVisible = false;
                        _this.$alert( '申请成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                        window.location.reload()
                    }
                    else{
                        _this.dialogFormVisible = false;
                        _this.$alert('申请失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
