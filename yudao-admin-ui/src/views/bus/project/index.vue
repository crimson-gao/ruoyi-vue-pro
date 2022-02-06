<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24" v-if="has_cross_dept_permission">
        <el-row >
          <el-col>
            <div class="head-container">
              <el-input v-model="deptName" placeholder="请输入部门名称" clearable size="small" prefix-icon="el-icon-search" style="margin-bottom: 20px"/>
            </div>
          </el-col>
          <el-col>
            <el-button @click="clearDept" :type="queryParams.deptId === undefined ?'primary':''">全选</el-button>
          </el-col>
        </el-row>
        <el-row>
          <div class="head-container">
            <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false" :filter-node-method="filterNode"
                     ref="tree" default-expand-all @node-click="handleNodeClick"/>
          </div>
        </el-row>

      </el-col>
      <el-col :span="20" :xs="24">
        <!-- 搜索工作栏 -->
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="名称" prop="name">
              <el-input v-model="queryParams.name" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery"/>
            </el-form-item>
            <el-form-item label="编号" prop="code">
              <el-input v-model="queryParams.code" placeholder="1811UZ800FS" clearable size="small" @keyup.enter.native="handleQuery"/>
            </el-form-item>
            <el-form-item label="登记编号" prop="registerCode">
              <el-input v-model="queryParams.registerCode" placeholder="HA002" clearable size="small" @keyup.enter.native="handleQuery"/>
            </el-form-item>
<!--            <el-form-item label="部门id" prop="deptId">-->
<!--              <el-input v-model="queryParams.deptId" placeholder="请输入部门id" clearable size="small" @keyup.enter.native="handleQuery"/>-->
<!--            </el-form-item>-->
            <el-form-item label="登记时间">
              <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                              type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="未选择" clearable size="small">
                <el-option label="待完工" value="0" />
                <el-option label="已完工" value="1" />
                <el-option label="已归档" value="2" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>

        <!-- 操作工具栏 -->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                       v-hasPermi="['bus:project:create']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                       v-hasPermi="['bus:project:export']">导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <!-- 列表 -->
        <el-table v-loading="loading" :data="list" :row-class-name="rowStyle"
        :border="true" @row-dblclick="handleDbClick" >
<!--          <el-table-column resizable label="id" align="center" prop="id" />-->
<!--          提示-->

        <el-table-column resizable label="id" align="center" prop="id">
          <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="双击打开材料清单" placement="top-start">
              <span>{{ scope.row.id }}</span>
          </el-tooltip>
          </template>
        </el-table-column>

          <el-table-column resizable label="类别" align="center" prop="type" />
          <el-table-column resizable label="名称" align="center" prop="name" />
          <el-table-column resizable label="编号" align="center" prop="code"
          >
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" content="双击打开材料清单" placement="top-start">
                <span>{{ scope.row.code }}</span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column resizable label="登记编号" align="center" prop="registerCode" />
          <el-table-column resizable v-if="has_cross_dept_permission" label="部门名称" align="center" prop="deptName" />
          <el-table-column resizable label="施工单位" align="center" prop="builder" />
          <!-- 这里是展示时间 -->
    <!--      <el-table-column label="登记时间" align="center" prop="createTime" width="180">-->
    <!--        <template slot-scope="scope">-->
    <!--          <span>{{ parseTime(scope.row.createTime) }}</span>-->
    <!--        </template>-->
    <!--      </el-table-column>-->

    <!--      <el-table-column label="完工时间" align="center" prop="builtTime" width="180">-->
    <!--        <template slot-scope="scope">-->
    <!--          <span>{{ parseTime(scope.row.builtTime) }}</span>-->
    <!--        </template>-->
    <!--      </el-table-column>-->
    <!--      <el-table-column label="归档时间" align="center" prop="doneTime" width="180">-->
    <!--        <template slot-scope="scope">-->
    <!--          <span>{{ parseTime(scope.row.doneTime) }}</span>-->
    <!--        </template>-->
    <!--      </el-table-column>-->
    <!--      <el-table-column label="状态，0为已创建" align="center" prop="status" />-->
          <el-table-column resizable label="备注" align="center" prop="note" />
          <el-table-column resizable v-if="hasViewMoneyPermission()"  label="预期付款" align="center" prop="requiredPayment" />
          <el-table-column resizable v-if="hasViewMoneyPermission()"  label="已付款" align="center" prop="paidPayment" />

<!--            <el-table-column label="创建者" align="center" prop="creator" />-->

          <el-table-column resizable label="更新时间" align="center" prop="updateTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updateTime) }}</span>
            </template>
          </el-table-column>
    <!--      <el-table-column label="更新者" align="center" prop="updater" />-->
          <el-table-column resizable :fit="true" fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-popover
                placement="top-start"
                title="项目进度"
                width="200"
                trigger="hover"
                content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
                <el-steps :space="200" :active="scope.row.status === undefined ? 1 : scope.row.status + 1" finish-status="success">
                  <el-step title="创建"></el-step>
                  <el-step title="施工"></el-step>
                  <el-step title="归档"></el-step>
                </el-steps>
<!--                <el-button class="operation" v-hasPermiDisable="['bus:project:update','bus:project:changeToBuilt']"  @click="alert('hello')">完工1</el-button>-->
<!--                <el-button class="operation" :disabled="true" @click="alert('hello')">完工2</el-button>-->
                <el-button class="operation"
                           v-hasPermi="['bus:project:update','bus:project:changeToBuilt']"
                           v-if="scope.row.status === undefined || scope.row.status === 0"
                           @click="handleChangeStatus(scope.row,'完工')">完工</el-button>
                <el-button class="operation"
                           v-hasPermi="['bus:project:update','bus:project:changeToDone']"
                           v-else-if="scope.row.status === 1"
                           @click="handleChangeStatus(scope.row,'归档')">归档</el-button>
                <el-button class="operation" size="mini" icon="el-icon-circle-check" type="text" slot="reference">进度</el-button>
              </el-popover>
              <el-button class="operation" size="mini" type="text" icon="el-icon-edit"  @click="handleUpdate(scope.row)"
                         v-hasPermi="['bus:project:update']">修改</el-button>
              <el-button class="operation" size="mini" type="text" icon="el-icon-delete"  @click="handleDelete(scope.row)"
                         v-hasPermi="['bus:project:delete']">删除</el-button>
<!--              <el-button class="operation" size="mini" type="text"  @click="handleManifest(scope.row)"-->
<!--                       icon="el-icon-circle-check">材料</el-button>-->
            </template>
          </el-table-column>
      </el-table>
        <!-- 分页组件 -->
        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                    @pagination="getList"/>
      </el-col>
    </el-row>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类别" prop="type">
              <el-input v-model="form.type" placeholder="请输入类别" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="编号" prop="code">
              <el-input v-model="form.code" placeholder="请输入编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登记编号" prop="registerCode">
              <el-input v-model="form.registerCode" placeholder="请输入登记编号" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
        <el-col :span="12" v-if="has_cross_dept_permission">
          <el-form-item label="归属部门" prop="deptId">
            <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true"
                        placeholder="请选择归属部门" :normalizer="normalizer"/>
          </el-form-item>
        </el-col>
<!--        <el-form-item v-if="isCreate" label="部门id" prop="deptId">-->
<!--          <el-input v-model="form.deptId" placeholder="请输入部门id" />-->
<!--        </el-form-item>-->
        <el-col :span="12">
          <el-form-item label="施工单位" prop="builder">
            <el-input v-model="form.builder" placeholder="请输入施工单位" />
          </el-form-item>
        </el-col>
        </el-row>
<!--        <el-form-item label="完工时间" prop="builtTime">-->
<!--          <el-date-picker clearable size="small" v-model="form.builtTime" type="date" value-format="yyyy-MM-dd" placeholder="选择完工时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="归档时间" prop="doneTime">-->
<!--          <el-date-picker clearable size="small" v-model="form.doneTime" type="date" value-format="yyyy-MM-dd" placeholder="选择归档时间" />-->
<!--        </el-form-item>-->
        <el-row>
          <el-form-item label="状态" prop="status" v-if="isCreate">
            <el-radio-group v-model="form.status">
              <el-radio :label="0" >待完工</el-radio>
              <el-radio :label="1" >已完工</el-radio>
              <el-radio :label="2" >已归档</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="需付款" v-hasPermi="['bus:project:update','bus:project:updateMoney']" prop="requiredPayment">
              <el-input v-model="form.requiredPayment" placeholder="请输入预期付款" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="已付款" v-hasPermi="['bus:project:update','bus:project:updateMoney']" prop="paidPayment">
              <el-input v-model="form.paidPayment" placeholder="请输入已付款金额" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 对话框- 材料清单 -->
    <el-dialog :title="title" :visible.sync="openManifest" width="80%" append-to-body>
      <ProjectManifest :parent_project_id="current_project_id">

      </ProjectManifest>
    </el-dialog>
  </div>
</template>

<script>
import { createProject, updateProject, deleteProject, getProject, getProjectPage, exportProjectExcel } from "@/api/bus/project";
import { hasPermissions } from '@/directive/permission/tools'
import {listSimpleDepts} from "@/api/system/dept";
import {listSimplePosts} from "@/api/system/post";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import store from '@/store'
import ProjectManifest from "@/views/bus/projectManifest";

const CROSS_DEPT_PERMISSION = ['bus:crossDept']
export default {
  name: "Project",
  components: {
    Treeselect ,
    ProjectManifest
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 弹出材料清单界面
      openManifest: false,
      deptOptions: undefined,
      postOptions: undefined,
      deptName: undefined,
      current_project_id: undefined,
      defaultProps: {
        children: "children",
        label: "name"
      },
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        code: null,
        registerCode: null,
        deptId: null,
        status: null,
      },
      isCreate: false,
      dept_id: store.getters && store.getters.dept_id,
      has_cross_dept_permission: hasPermissions(CROSS_DEPT_PERMISSION),
      // 表单参数
      form: {
        id: undefined,
        type: undefined,
        name: undefined,
        code: undefined,
        registerCode: undefined,
        deptId: undefined,
        builder: undefined,
        builtTime: undefined,
        doneTime: undefined,
        status: undefined,
        requiredPayment: undefined,
        paidPayment: undefined,
        note: undefined,
      },
      // 表单校验
      rules: {
        type: [{ required: true, message: "类别不能为空", trigger: "blur" }],
        name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
        code: [{ required: true, message: "编号不能为空", trigger: "blur" }],
        registerCode: [{ required: true, message: "登记编号不能为空", trigger: "blur" }],
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }],
        deptId: [{ required: true, message: "部门id不能为空", trigger: "blur" }],
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    if(!this.has_cross_dept_permission){
      this.queryParams.deptId = this.dept_id
    }
    this.getList();
    this.getTreeselect();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      this.ensureDeptPermission();
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getProjectPage(params).then(response => {
        this.list = response.data.list;
        // 替换成部门名字
        for(let item of this.list){
          if(item.deptId !== undefined){
            item.deptName = this.getDeptName(item.deptId)
          }
        }
        this.total = response.data.total;
        this.loading = false;
        // console.log("get list:")
        // console.log(this.list);
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        type: undefined,
        name: undefined,
        code: undefined,
        registerCode: undefined,
        deptId: undefined,
        builder: undefined,
        builtTime: undefined,
        doneTime: undefined,
        status: undefined,
        requiredPayment: undefined,
        paidPayment: undefined,
        note: undefined,
      };
      this.resetForm("form");
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isCreate = true;
      this.open = true;
      this.title = "添加项目";
      this.form.status = 0;
      this.ensureDeptPermission();
      this.getTreeselect();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      this.getTreeselect();
      getProject(id).then(response => {
        this.form = response.data;
        this.isCreate = false;
        this.open = true;
        this.title = "修改项目";
      });
    },
    ensureDeptPermission() {
      if(!this.has_cross_dept_permission){
        this.queryParams.deptId = this.dept_id
        this.form.deptId = this.dept_id
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          // console.log("update:")
          // console.log(this.form);
          updateProject(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createProject(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除项目编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteProject(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有项目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectExcel(params);
        }).then(response => {
          this.downloadExcel(response, '项目.xls');
        })
    },
    submitChangeStatus() {
      // console.log("this",this)
      // console.log(this.form);
      // 修改 status
      if(this.form.status === 0){
        // 变为完工
        this.form.status = 1
        this.form.builtTime = new Date().getTime();
      }else if(this.form.status === 1){
        this.form.status = 2
        this.form.doneTime = new Date().getTime();
      }
      // 提交
      if (this.form.id != null) {
        // console.log("流转:")
        // console.log(this.form);
        updateProject(this.form).then(response => {
          this.msgSuccess("流转状态成功");
          this.getList();
        });
      }
    },
    handleChangeStatus(row, nextStatus) {
      let vueThis = this;
      this.$confirm('此项目状态将流转为【' + nextStatus+ '】, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        vueThis.reset();
        const id = row.id;
        getProject(id).then(response => {
          vueThis.form = response.data;
          vueThis.title = "项目流转";
          // console.log("vue",vueThis)
        }).then(() =>{
          vueThis.submitChangeStatus();
          this.$message({
            type: 'success',
            message: '流转成功!'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消流转'
        });
      });
    },
    rowStyle({row,rowIndex}) {
      if(row.status === 1){
        return 'building-row common-row'
      }else if(row.status === 2){
        return 'built-row common-row'
      }else{
        return 'normal common-row'
      }
    },
    hasViewMoneyPermission() {
      // console.log(hasPermissions)
      return hasPermissions(['bus:project:viewMoney'])
    },
    /** 查询部门下拉树结构 + 岗位下拉 */
    getTreeselect() {
      listSimpleDepts().then(response => {
        // 处理 deptOptions 参数
        this.deptOptions = [];
        this.deptOptions.push(...this.handleTree(response.data, "id"));
      });
      listSimplePosts().then(response => {
        // 处理 postOptions 参数
        this.postOptions = [];
        this.postOptions.push(...response.data);
      });
    },
    // 格式化部门的下拉框
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children
      }
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    clearDept() {
      this.queryParams.deptId = undefined;
      this.deptName = undefined;
      this.getList();
    },
    handleManifest(row) {
      this.openManifest = true
      this.current_project_id = row.id
    },
    handleDbClick(row,col,event){
      console.log(row)
      this.handleManifest(row)
    }
  }
};
</script>
<style>
.el-table .building-row {
  background: oldlace;
}
.el-table .normal {
}
.el-table .built-row {
  background: #f0f9eb;
}

.operation {
  width: 50%;
  display: inline;
}
.operation :hover{
  transform: scale(1.3);
}
</style>
