<template>
    <!--    赛事创建-->
    <div>
        <el-form :label-position="labelPosition" label-width="100px" >
            <el-form-item label="赛事ID">
                <el-input v-model="id"></el-input>
            </el-form-item>
            <el-form-item label="公告">
                <el-input
                        type="textarea"
                        :rows="2"
                        placeholder="请输入内容"
                        v-model="intro">
                </el-input>
            </el-form-item>
        </el-form>

        <el-button type="primary" round @click.native="submit">确定</el-button>
        <el-button type="info" round>取消</el-button>

        <!--        弹窗-->
        <el-dialog title="赛事信息" :visible.sync="dialogTableVisible1" width="1000px">
            <el-form :label-position="labelPosition" label-width="80px">
                <el-form-item label="赛事ID">
                    <span>{{showid}}</span>
                </el-form-item>
                <el-form-item label="赛事名称">
                    <span>{{showname}}</span>
                </el-form-item>
                <el-form-item label="赛事时间">
                    <span>{{showintro}}</span>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "MatchIntro",
        data(){
            return {
                dialogTableVisible1:false,
                labelPosition: 'right',
                id:'',
                intro:'',
                showid:'null',
                showname:'null',
                showintro:'null'
            }
        },
        methods:{
            submit(){
                if(this.id == ''||this.intro == '['){
                    this.$message.error('没有填写完成！！！')
                }else {
                    this.dialogTableVisible1 = true;
                    const _this= this;
                    axios.get('http://localhost:8181/gameData/Intro/'+this.id+"/"+this.intro).then(function (resp) {
                        _this.showid = resp.data.gameid;
                        _this.showname = resp.data.gamename;
                        _this.showintro =resp.data.intro;
                    })
                }
            },
        }
    }
</script>

<style scoped>

</style>
