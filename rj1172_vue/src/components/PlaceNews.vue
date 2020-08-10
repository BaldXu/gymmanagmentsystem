<template>
    <div>
    <el-form>
        <span  style="font-size: 20px;line-height:40px;white-space:pre-wrap;">{{news.msg}}</span>
        <el-button type="primary" @click="update" style="float: right;margin-top: 40px">
            修改收费标准
        </el-button>
    </el-form>
    <el-dialog title="收费标准" :visible.sync="dialogFormVisible1" :show-close="true">
        <el-input
                type="textarea"
                placeholder="请输入内容"
                v-model="news.msg"
                :rows="10"
                style="font-size: 20px;">
        </el-input>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="sureupdate">确认</el-button>
            <el-button plain @click="can">取消</el-button>
        </div>
    </el-dialog>
        <el-dialog title="确认消息" :visible.sync="dialogFormVisible2" :show-close="true">
            确认是否修改收费标准及开馆时间
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="sure">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PlaceNews",
        data() {
            return {
                news: {type: 'news', msg: '234'},
                dialogFormVisible1: false,
                dialogFormVisible2: false,
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/place/placenews";
            axios.get(url,{params:{type:this.news.type,msg:this.news.msg}}).then(function (resp) {
                _this.news = resp.data;
            })
        },
        methods: {
            sureupdate() {
                this.dialogFormVisible1 = false;
                this.dialogFormVisible2 = true;
            },
            cancel() {
                this.dialogFormVisible2 = false;
            },
            update() {
                this.dialogFormVisible1 = true;
            },
            can() {
                this.dialogFormVisible1 = false;
            },
            sure() {
                const _this = this;
                const url = "http://localhost:8181/place/newsupdate";
                axios.post(url, {type: "news",msg:this.news.msg}).then(function (resp) {
                    if (resp.data === "success") {
                        _this.dialogFormVisible2 = false;
                        _this.$alert( '修改信息成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                    else{
                        _this.dialogFormVisible2 = false;
                        _this.$alert('修改信息失败!', '消息', {
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
