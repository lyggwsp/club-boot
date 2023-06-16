<template>
    <div class="authority">
        <a-table
                rowKey="id"
                :pagination={...pagination,onChange:onPageChange}
                :columns="columns" :data-source="data"
        >
            <!--     页面头部信息       -->
            <template #title>
                <div class="flex">
                    角色列表
                </div>
                <a-form style="margin-top: 10px">
                    <a-row>
                        <a-col :span="5">
                            <a-form-item label="角色名称">
                                <a-input placeholder="请输入角色名称"></a-input>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="角色代码">
                                <a-input placeholder="请输入角色标识"></a-input>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="状态">
                                <a-input placeholder="角色状态"></a-input>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="创建时间">
                                <a-range-picker
                                        show-time
                                        format="YYYY-MM-DD HH:mm:ss"
                                        value-format="YYYY-MM-DD HH:mm:ss"
                                />
                            </a-form-item>
                        </a-col>
                    </a-row>
                    <a-button type="primary">
                        <search-outlined/>
                        搜索
                    </a-button>
                    <a-button style="margin-left: 10px">
                        <sync-outlined/>
                        重置
                    </a-button>
                </a-form>
                <div style="margin-top: 1%">
                    <a-button type="primary" style="background-color:#bed7ff;color: #0ea5e9; ">
                        <template #icon>
                            <PlusOutlined/>
                        </template>
                        新增
                    </a-button>
                    <a-button type="text" style="background-color:#f08705;margin-left: 10px;color: white">
                        <template #icon>
                            <PlusOutlined/>
                        </template>
                        导出
                    </a-button>
                </div>
            </template>

            <template #headerCell="{ column }">
                <template v-if="column.key === 'name'">
                    <span>
                      <smile-outlined/>
                      Name
                    </span>
                </template>
            </template>

            <template #bodyCell="{ column, record }">
                <template v-if="column.key === 'name'">
                    <a>
                        {{ record.name }}
                    </a>
                </template>
                <template v-else-if="column.key === 'tags'">
                    <span>
                      <a-tag
                              v-for="tag in record.tags"
                              :key="tag"
                              :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
                      >
                        {{ tag.toUpperCase() }}
                      </a-tag>
                    </span>
                </template>
                <template v-else-if="column.key === 'type'">
                    <a-tag v-if="record.type === 1" color="blue">自定义</a-tag>
                    <a-tag v-if="record.type === 0" color="red">内置</a-tag>
                </template>
                <template v-else-if="column.key === 'status'">
                    <a-switch/>
                </template>
                <template v-else-if="column.key === 'action'">
                    <span>

                      <a>  <edit-outlined/> 修改</a>
                      <a-divider type="vertical"/>
                      <a><check-circle-outlined/>菜单权限</a>
                      <a-divider type="vertical"/>
                      <a class="ant-dropdown-link">
                       <delete-outlined/> 删除
                      </a>
                    </span>
                </template>
            </template>
        </a-table>
    </div>
</template>

<script lang="ts" setup>
import {ref, onMounted, reactive} from 'vue'
import {SmileOutlined} from '@ant-design/icons-vue';
import Role from "@/pages/system/role/index";
import {Base} from "@/common/vo/base";
import {getRoleCondition} from "@/service/system";
import {Condition} from "@/common/vo/condition";

const columns = [
    {
        title: '角色编号',
        dataIndex: 'id',
        key: 'id',
    },
    {
        name: '角色名称',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '角色标识',
        dataIndex: 'code',
        key: 'code',
    },
    {
        title: '角色类型',
        dataIndex: 'type',
        key: 'type',
    },
    {
        title: '显示排序',
        dataIndex: 'sort',
        key: 'sort',
    },
    {
        title: '状态',
        key: 'status',
        dataIndex: 'status',
    },
    {
        title: '创建时间',
        key: 'createTime',
        dataIndex: 'createTime',
    },
    {
        title: '操作',
        key: 'action',
        align: "center"
    },
];

export interface Role extends Base {
    name?: string
    code?: string
    sort?: number
    status?: number
    type?: number
}

// 数据信息
const data: Role[] = reactive<Role[]>([]);

// 页数页码等信息
const pagination = reactive({
    current: 0,
    pageSize: 5,
    total: 0
})

/**
 * 页面挂载
 */
onMounted(() => {
    getData()
})

/**
 * 获取数据
 */
const getData = () => {
    getRoleCondition(pagination.current, pagination.pageSize).then((res) => {
        console.log(res)
        const {records, current, size, total} = res.data
        data.splice(0, data.length, ...records);
        pagination.current = current
        pagination.pageSize = size
        pagination.total = total
        console.log(data)
    }).catch(errMsg => {
        console.error("获取角色信息失败！", errMsg)
    })
}
/**
 * 当用户点击下一页的时候会调用该方法
 * @param page 第几页
 * @param pageSize 页面大小
 */
const onPageChange = (page: number, pageSize: number) => {
    pagination.current = page
    pagination.pageSize = pageSize
    getData()
}


</script>

<style scoped>

</style>
