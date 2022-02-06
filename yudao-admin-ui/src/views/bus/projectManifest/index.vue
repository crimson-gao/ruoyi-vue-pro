<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item v-if="parent_project_id === undefined" label="所属项目" prop="projectId">
        <el-input v-model="queryParams.projectId" placeholder="请输入所属项目" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-row>
        <el-col :span="24">
          <el-form-item label="物料编码" prop="materialId">
            <el-select v-model="queryParams.materialId" filterable remote reserve-keyword
                       clearable @clear="clearSelector(querySelector,queryParams)"
                       placeholder="请输入编码" :remote-method="queryMaterialCode"
                       :loading="querySelector.select_loading">
              <el-option v-for="item in querySelector.materialOptions"
                         :key="item.id"
                         :label="item.code + ' ' + item.name"
                         :value="item.id"></el-option>
            </el-select>
            <!--          <el-input v-model="form.materialCode" placeholder="HA001" />-->
          </el-form-item>
        </el-col>
      </el-row>
<!--      <el-form-item label="物料编码" prop="materialCode">-->
<!--        <el-input v-model="queryParams.materialCode" placeholder="HA001" clearable size="small" @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
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
                   v-hasPermi="['bus:project-manifest:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['bus:project-manifest:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 展示列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column v-if="parent_project_id === undefined" label="所属项目" align="center" prop="projectId">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top" @show="showProjectInfo(scope.row.projectId)">
            <el-container
              v-loading="project_map[scope.row.projectId] === undefined">
              <div v-if="project_map[scope.row.projectId] !== undefined">
                <p v-for="(name,key) in project_props_names">
                  <span>
                    {{name +': '}}
                  </span>
                  <span>
                    {{ (project_map[scope.row.projectId] && project_map[scope.row.projectId][key]) }}
                  </span>
                </p>
              </div>
            </el-container>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.projectId }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
<!--      <el-table-column label="物料编码" align="center" prop="materialId" />-->
      <el-table-column label="名称" align="center" prop="materialId" >
        <template slot-scope="scope">
          <span>{{ materialFormatter(scope.row.materialId,'name') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="物料编码" align="center" prop="materialId" >
        <template slot-scope="scope">
<!--          <span>{{ materialFormatter(scope.row.materialId,'code') }}</span>-->
          <el-popover trigger="hover" placement="top">
            <div v-if="material_map[scope.row.materialId]">
              <p v-for="(name,key) in material_props_names">
                {{ name + ': ' + material_map[scope.row.materialId][key]}}
              </p>
            </div>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ materialFormatter(scope.row.materialId,'code') }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="申报数量" align="center" prop="requiredQuantity" />
      <el-table-column label="到货数量" align="center" prop="receivedQuantity" />
      <el-table-column label="规格" align="center" prop="materialId" >
        <template slot-scope="scope">
          <span>{{ materialFormatter(scope.row.materialId,'spec') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="创建者" align="center" prop="creator" />-->
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="更新者" align="center" prop="updater" />-->
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['bus:project-manifest:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['bus:project-manifest:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-if="parent_project_id === undefined && isCreate" label="所属项目" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入所属项目" />
        </el-form-item>
<!--        下拉搜索框 -->
        <el-row>
          <el-col :span="24">
            <el-form-item v-if="isCreate" label="物料编码" prop="materialId">
              <el-select v-model="form.materialId" filterable remote reserve-keyword
                         clearable @clear="clearSelector(formSelector,form)"
                         placeholder="请输入编码" :remote-method="formMaterialCode"
                         :loading="formSelector.select_loading">
                <el-option v-for="item in formSelector.materialOptions"
                :key="item.id"
                           :label="item.code + ' ' + item.name"
                           :value="item.id"></el-option>
              </el-select>
    <!--          <el-input v-model="form.materialCode" placeholder="HA001" />-->
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="申报数量" prop="requiredQuantity">
              <el-input v-model="form.requiredQuantity" placeholder="请输入申报数量" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="到货数量" prop="receivedQuantity">
              <el-input v-model="form.receivedQuantity" placeholder="请输入到货数量" />
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
  </div>
</template>

<script>
import { createProjectManifest, updateProjectManifest, deleteProjectManifest, getProjectManifest, getProjectManifestPage, exportProjectManifestExcel } from "@/api/bus/projectManifest";
import { listMaterial, getMaterialPage } from "@/api/bus/material";
import { getProject } from "@/api/bus/project";

export default {
  name: "ProjectManifest",
  components: {
  },
  props:['parent_project_id'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 查询选择器
      querySelector:{
        select_loading: false,
        materialOptions: []
      },
      // 表单选择器
      formSelector:{
        select_loading: false,
        materialOptions: []
      },
      // 没有任何限制条件查询返回的材料列表
      materialList: [],
      materialTotalCnt: undefined,
      // 查询 material 参数
      queryMaterialParams: {
        pageNo: 1,
        pageSize: 80,
        code: null,
        name: null
      },
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目物料清单列表，展示用
      list: [],
      // 对应的物料编码信息，展示用
      material_map: {},
      // 物料编码的属性信息
      material_props_names: {
        id:'id',
        name: '名称',
        code:'物料编码',
        spec:'规格',
        note:'物料备注',
      },
      // 项目信息
      project_props_names: {
        id:'id',
        type: '类别',
        name: '名称',
        code:'项目编号',
        registerCode: '登记编号',
        builder:'施工单位',
        note:'项目备注',
      },
      //懒加载的 project信息
      project_map:{},
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      isCreate: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        projectId: null,
        materialId: null,
        materialCode: null,
      },
      // 表单参数，创建修改时使用
      form: {
        id: undefined,
        projectId: undefined,
        materialId: undefined,
        requiredQuantity: undefined,
        receivedQuantity: undefined,
        note: undefined,
      },
      // 表单校验
      rules: {
        projectId: [{ required: true, message: "所属项目不能为空", trigger: "blur" }],
        materialId: [{ required: true, message: "物料id不能为空", trigger: "blur" }],
        // materialCode: [{ message: "物料编码不能为空", trigger: "blur" }],
        requiredQuantity: [{ required: true, message: "申报数量不能为空", trigger: "blur" }],
        receivedQuantity: [{ required: false, default:'0', message: "到货数量默认为0", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    // 初始化 material 列表
    let params = {...this.queryMaterialParams}
    params.code = undefined
    params.name = undefined
    this.loadMaterialList(params).then((response)=>{
        this.materialList = response.list
        this.materialTotalCnt = response.total
        this.querySelector.materialOptions = this.materialList;
        this.formSelector.materialOptions = this.materialList;
      }
    );
  },
  mounted() {

  },
  watch: {
    parent_project_id (newV, oldV){
      if(newV !== oldV){
        this.parent_project_id = newV
        this.getList()
      }
    }
  },
  methods: {
    // 查询 material 的名字、编码等信息，显示在表格中
    getMaterialName() {
      if( this.list.length === 0){
        return {data:[]}
      }
      let ids = []
      for(let manifest of this.list){
        ids.push(manifest.materialId)
      }
      let data = [...new Set(ids)].join()
      // console.log(data)
      return listMaterial(data)
    },
    /** 查询列表 */
    getList() {
      let vueThis = this;
      this.loading = true;
      this.ensureParentProjectId();
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getProjectManifestPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
      }).then(function(){
        return vueThis.getMaterialName()
      }).then((response)=>{
        vueThis.material_map = {}
        for(let material of response.data){
          vueThis.material_map[material.id]=material
        }
        console.log(vueThis.material_map)
        vueThis.loading = false;
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
        projectId: undefined,
        materialId: undefined,
        requiredQuantity: undefined,
        receivedQuantity: undefined,
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
      this.ensureParentProjectId();
      this.isCreate = true;
      this.open = true;
      this.title = "添加项目物料清单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      this.ensureParentProjectId();
      getProjectManifest(id).then(response => {
        this.form = response.data;
        this.isCreate = false;
        this.open = true;
        this.title = "修改项目物料清单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateProjectManifest(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createProjectManifest(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除项目物料清单编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteProjectManifest(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      this.ensureParentProjectId();
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有项目物料清单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectManifestExcel(params);
        }).then(response => {
          this.downloadExcel(response, '项目物料清单.xls');
        })
    },
    // 保证 projectId，在添加条目、查询条目、导出条目时起作用
    ensureParentProjectId() {
      if(this.parent_project_id !== undefined){
        this.queryParams.projectId = this.parent_project_id
        this.form.projectId = this.parent_project_id
      }
    },
    // 获取 material 的属性
    materialFormatter(materialId,prop) {
      // console.log("formatter",materialId,prop)
      // console.log(this.material_map)
      return materialId && this.material_map[materialId] && this.material_map[materialId][prop]
    },
    // 更新
    updateSelectorOptions(query,selector,list){
      selector.materialOptions = list.filter(
        item =>{
          let q = query.toLowerCase()
          // 可以搜索 编码或者名字或者两者的组合
          return item.code.toLowerCase().indexOf(q) > -1 ||
            item.name.toLowerCase().indexOf(q) > -1 ||
            (item.code + ' ' + item.name).toLowerCase().indexOf(q) > -1
        }
      )
      // console.log("options",selector.materialOptions)
    },
    async handleMaterialCode(query,selector) {
      if(query !== ""){
        // 如果暂存存储的材料列表，包含不了所有的材料，必须重新查询
        if(this.materialTotalCnt !== undefined && this.materialTotalCnt > this.queryMaterialParams.pageSize){
          selector.select_loading = true
          let params1 = {...this.queryMaterialParams}
          let params2 = {...this.queryMaterialParams}
          params1.code = query
          params1.name = undefined
          params2.code = undefined
          params2.name = query
          let vueThis = this
          Promise.all([this.loadMaterialList(params1),this.loadMaterialList(params2)]).then(
            ([resp1,resp2])=>{
              let m = {}
              let res = []
              for(let i of resp1.list.concat(resp2.list)){
                m[i.id]=i
              }
              for(let key in m){
                res.push(m[key])
              }
              // console.log("res",res)
              vueThis.updateSelectorOptions(query,selector,res)
              selector.select_loading = false
            }
          )
        }else{
          this.updateSelectorOptions(query,selector,this.materialList)
          selector.select_loading = false
        }
      }else{
        selector.materialOptions = this.materialList
        selector.select_loading = false
      }
    },
    // material 搜索框
    formMaterialCode(query) {
      this.handleMaterialCode(query,this.formSelector)
    },
    queryMaterialCode(query) {
      this.handleMaterialCode(query,this.querySelector)
    },
    loadMaterialList(params) {
      // id , code , spec, note , name
      // 处理查询参数
      // let params = {...this.queryMaterialParams};
      // 执行查询
      return getMaterialPage(params).then(response => {
        return response.data;
      })
    },
    clearSelector(selector,root) {
      if(root !== undefined){
        root.materialId = undefined
      }
      selector.materialOptions = this.materialList
      selector.select_loading = false
    },
    showProjectInfo(projectId) {
      if(this.project_map[projectId] !== undefined){
        return
      }
      let vueThis = this
      getProject(projectId).then((response)=>{
        vueThis.$set(vueThis.project_map,projectId,response.data)
        // console.log(vueThis)
      })
    }
  }
};
</script>
