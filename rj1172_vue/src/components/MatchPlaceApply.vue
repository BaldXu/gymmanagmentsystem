<template>
    <!--    赛事场地申请-->
    <div>
        <el-form :label-position="labelPosition" label-width="100px">
            <el-form-item label="赛事ID">
                <el-input
                        v-model="matchId"
                        type="number"
                ></el-input>
            </el-form-item>
            <el-form-item label="场地位置">
                <el-input v-model="talbeList.gameplacename"></el-input>
            </el-form-item>
            <el-form-item label="申请时间">
                <!--                日期-->
                <el-date-picker
                        v-model="talbeList.applytime"
                        type="date"
                        format=" MM 月 dd 日"
                        value-format="MM月dd日"
                        placeholder="选择日期">
                </el-date-picker>
                <!--                开始时间-->
                <el-time-select
                        placeholder="起始时间"
                        v-model="talbeList.starttime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30'
                 }">
                </el-time-select>
                <!--                结束时间-->
                <el-time-select
                        placeholder="结束时间"
                        v-model="talbeList.endtime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30',
                        minTime: talbeList.starttime
                 }">
                </el-time-select>
            </el-form-item>
            <el-form-item label="申请理由">
                <el-input
                        v-model="applyreason"
                        type="textare"
                ></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click.native="submit">提交</el-button>
        <el-button type="info">取消</el-button>

        <!--        弹窗-->
        <el-dialog title="赛事信息" :visible.sync="dialogTableVisible1" width="1000px">
            <el-form :label-position="labelPosition" label-width="80px">
                <el-form-item label="赛事ID">
                    <span>{{showid}}</span>
                </el-form-item>
                <el-form-item label="赛事名称">
                    <span>{{showname}}</span>
                </el-form-item>
                <el-form-item label="赛事地点">
                    <span>{{showplace}}</span>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "gameplacenameApply",
        data() {
            return {
                dialogTableVisible1:false,
                labelPosition: 'right',
                matchId: '',
                applyreason:'',

                talbeList: {
                    gameplacename: '',
                    applytime: '',
                    starttime: '',
                    endtime: ''
                },
                showid:'null',
                showname:'null',
                showplace:'null'
            }
        }
        ,
        methods: {
            show() {
                //展示开始时间和结束时间的格式
                // alert(typeof this.starttime);
                // alert(typeof this.talbeList.applytime+""+ this.talbeList.applytime);
            }
            ,
            submit() {
                this.reset();
                if (this.matchId == '' || this.talbeList.gameplacename == '' || this.talbeList.applytime == '' || this.talbeList.starttime == '' || this.talbeList.endtime == '' | this.talbeList.applyreason == '') {
                    this.$message.error('没有填写完毕！！');
                } else {
                    this.dialogTableVisible1 = true;
                    const _this = this;

                    // axios.post('http://localhost:8181/gamePlace/placeApply', this.talbeList).then(function (resp) {
                    //     if (resp.data == 'success') {
                    //         _this.$message({
                    //             message: '添加成功！！',
                    //             type: 'success'
                    //         });
                    //     } else {
                    //         _this.$message.error('未知原因添加失败');
                    //     }
                    // })
                    axios.get('http://localhost:8181/gameData/placeUpdata/'+this.matchId+"/"+this.talbeList.gameplacename).then(function (resp) {
                        _this.showname = resp.data.gamename;
                        _this.showplace = resp.data.gameplace;
                        _this.showid = resp.data.gameid;
                    })
                }
            },
            reset(){
                this.showname = 'null';
                this.showplace = 'null';
                this.showid= 'null';
            }
        }
    }
</script>

<style scoped>

</style>
