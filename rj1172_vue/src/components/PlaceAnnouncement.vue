<template>
    <div>
        <el-form>
            <span  style="font-size: 20px;line-height:40px;white-space:pre-wrap;">{{news.msg}}</span>
            <el-button type="primary" @click="update" style="float: right;margin-top: 40px">
                发布公告
            </el-button>
        </el-form>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="以往公告"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="title"
                    label=""
                    width="700">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                page-size="5"
                :total="total"
                @current-change="page"
                style="text-align: center">
        </el-pagination>
        <el-dialog title="发布公告" :visible.sync="dialogFormVisible1" :show-close="true">
            <el-input
                    placeholder="请输入标题"
                    v-model="news.title"
                    style="margin-bottom: 10px">
            </el-input>
            <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="news.msg"
                    :rows="10">
            </el-input>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="sureupdate">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>
        <el-dialog title="确认消息" :visible.sync="dialogFormVisible2" :show-close="true">
            确认是否发布公告
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="sure">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>
        <el-dialog :title="title" :visible.sync="dialogFormVisible3" :show-close="true">
            {{announce}}
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="cancel">确认</el-button>
            </div>
        </el-dialog>


    </div>
</template>

<script>
    export default {
        name: "PlaceAnnouncement",
        data() {
            return {
                news: {type: 'news', msg: '234',title:''},
                announce:'',
                title:'',
                total:'',
                dialogFormVisible1: false,
                dialogFormVisible2: false,
                dialogFormVisible3: false,
                tableData: []
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/place/placeannouncement";
            axios.get(url,{params:{type:this.news.type,msg:this.news.msg}}).then(function (resp) {
                _this.news = resp.data;
            });
            const url1 = "http://localhost:8181/place/notices/1/5";
            axios.get(url1).then(function (resp) {
                _this.tableData = resp.data.content;
                _this.total=resp.data.totalElements
            })
        },
        methods: {
            page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/notices/"+currentPage+"/5";
                axios.get(url).then(function (resp) {
                    _this.tableData = resp.data.content;
                    _this.total=resp.data.totalElements
                })
            },
            handleClick(row) {
                console.log(row);
                this.dialogFormVisible3 = true;
                this.announce = row.msg;
                this.title = row.title
            },
            sureupdate() {
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = true;
            },
            cancel() {
                this.dialogFormVisible2 = false;
                this.dialogFormVisible1 = false;
                this.dialogFormVisible3 = false;
            },
            update() {
                this.dialogFormVisible1 = true;
            },
            sure() {
                const _this = this;
                const url = "http://localhost:8181/place/announcementadd";
                axios.post(url, {type: "announcement",msg:this.news.msg,title:this.news.title}).then(function (resp) {
                    if (resp.data === "success") {
                        _this.dialogFormVisible2 = false;
                        _this.$alert( '发布公告成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                    else{
                        _this.dialogFormVisible2 = false;
                        _this.$alert( '发布公告失败!', '消息', {
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
