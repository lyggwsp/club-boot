<template>
    <div class="authority">
        <a-alert v-if="alert_show" :message="message" type="error" show-icon style="width: 60%;margin: auto"/>
        <a-alert v-if="succeed_alert_show" :message="succeed_alert_message" type="success" show-icon
                 style="width: 60%;margin: auto"/>
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
                <a-form style="margin-top: 10px"
                        :model="conditionForm"
                        name="basic"
                        autocomplete="off"
                        @finish="finish_condition"
                >
                    <a-row>
                        <a-col :span="5">
                            <a-form-item label="角色名称" :name="['name']">
                                <a-input v-model:value="conditionForm.name"
                                         placeholder="请输入角色名称"></a-input>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="角色代码" :name="['code']">
                                <a-input placeholder="请输入角色标识"
                                         v-model:value="conditionForm.code"></a-input>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="状态" :name="['status']">
                                <a-select
                                        v-model:value="conditionForm.status"
                                        label-in-value
                                        style="width: 120px"
                                        :options="options"
                                        :default-active-first-option="true"
                                        :placeholder="options[0].label"
                                ></a-select>
                            </a-form-item>
                        </a-col>
                        <a-col :span="1"></a-col>
                        <a-col :span="5">
                            <a-form-item label="创建时间"
                                         :name="['start_end_time']">
                                <a-range-picker
                                        show-time
                                        format="YYYY-MM-DD HH:mm:ss"
                                        value-format="YYYY-MM-DD HH:mm:ss"
                                        v-model:value="conditionForm.start_end_time"
                                />
                            </a-form-item>
                        </a-col>
                    </a-row>
                    <div class="flex">
                        <a-form-item>
                            <a-button type="primary" html-type="submit">
                                <search-outlined/>
                                搜索
                            </a-button>
                        </a-form-item>
                        <a-button style="margin-left: 10px" @click="clearForm">
                            <sync-outlined/>
                            重置
                        </a-button>
                    </div>
                </a-form>
                <div style="margin-top: 1%">
                    <a-button type="primary"
                              style="background-color:#bed7ff;color: #0ea5e9; "
                              @click="showModal">
                        <template #icon>
                            <PlusOutlined/>
                        </template>
                        新增
                    </a-button>
                    <a-button type="text" @click="exportRoleInfos"
                              style="background-color:#f08705;margin-left: 10px;color: white">
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
                    <a-switch
                            :checked="record.status === 1"
                            @click="changeStatus(record.id, record.status ? 0 : 1)"
                            checked-children="启用" un-checked-children="禁用"
                    />
                </template>
                <template v-else-if="column.key === 'action'">
                    <span>

                      <a @click="updateShowModel(record)">  <edit-outlined/> 修改</a>
                      <a-divider type="vertical"/>
                      <a @click="show_assign_role_model(record)"><check-circle-outlined/>菜单权限</a>
                      <a-divider type="vertical"/>
                      <a class="ant-dropdown-link" @click="deleteRole(record.id)">
                       <delete-outlined/> 删除
                      </a>
                    </span>
                </template>
            </template>
        </a-table>
    </div>
  <!--增加和删除角色-->
    <a-modal v-model:visible="modelShow"
             :title="title"
             @ok="onFinish"
             cancelText="取消"
             okText="确定"
             :destroyOnClose="true">
        <a-alert v-if="addOrUpdateMessageShow" :message="addOrUpdateMessage" type="error" show-icon
                 style="width: 60%;margin: auto;margin-bottom: 10px"/>
        <a-form
                :model="roleForm"
                name="nest-messages"
        >
            <a-form-item v-if="roleForm.id != undefined"
                         :name="['id']"
                         label="角色编号">
                <a-input v-model:value="roleForm.id" :disabled="true"/>
            </a-form-item>
            <a-form-item :name="['name']" label="角色名称"
                         :rules="[{  required: true, message: '角色名称不能为空！' }]">
                <a-input v-model:value="roleForm.name"/>
            </a-form-item>
            <a-form-item :name="['code']" label="角色代码"
                         :rules="[{  required: true, message: '角色代码不能为空！' }]">
                <a-input v-model:value="roleForm.code"/>
            </a-form-item>
            <a-form-item :name="['sort']" label="角色排序"
                         :rules="[{  required: true, message: '角色排序不能为空！' }]">
                <a-input-number v-model:value="roleForm.sort" :min="0" :max="1024"/>
            </a-form-item>
            <a-form-item :name="['status']" label="是否启用">
                <a-radio-group v-model:value="roleForm.status">
                    <a-radio :value="1">是</a-radio>
                    <a-radio :value="0">否</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item :name="['description']" label="角色描述">
                <a-textarea v-model:value="roleForm.description"/>
            </a-form-item>
        </a-form>
    </a-modal>

    <a-modal v-model:visible="assign_role_menu_model_show"
             title="分配菜单权限"
             cancelText="取消"
             okText="确定"
             @ok="assign_role_menu"
             :destroyOnClose="true">
        <a-form :model="assign_role_form">
            <a-form-item :name="['name']" label="角色名称">
                <a-input v-model:value="assign_role_form.name" :disabled="true"/>
            </a-form-item>
            <a-form-item :name="['code']" label="角色代码">
                <a-input v-model:value="assign_role_form.code" :disabled="true"/>
            </a-form-item>
            <a-form-item label="菜单权限 菜单分配">
                <a-tree
                        v-model:checkedKeys="checkedKeys"
                        default-expand-all
                        checkable
                        :height="300"
                        :tree-data="menuTree"
                >
                </a-tree>
            </a-form-item>
        </a-form>
    </a-modal>

</template>

<script lang="ts" setup>
import {ref, onMounted, reactive, watch} from 'vue'
import {SmileOutlined} from '@ant-design/icons-vue';
import Role from "@/pages/system/role/index";
import {Base} from "@/common/vo/base";
import {
    addRoleInfo, assignRoleMenu,
    checkRoleStatus, exportRoleInfoAPI, getPermissionListRole,
    getRoleByCondition,
    getRoleCondition, getSimpleList,
    removeRole,
    RoleCondition, SaveUpdateRoleData, updateRoleInfo
} from "@/service/system";
import type {SelectProps, TreeProps} from 'ant-design-vue'
import download from "@/utils/download";

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
    current: 1,
    pageSize: 5,
    total: 0
})

/**
 * 页面挂载
 */
onMounted(() => {
    getData()
    getTreeData();
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

const message = ref<string>()
const alert_show = ref<boolean>(false)
/**
 * 更新角色状态是否启用
 * @param id
 * @param nStatus
 */
const changeStatus = (id: number, nStatus: number) => {
    // 判断角色状态
    const status = nStatus === 1;
    checkRoleStatus(id, status)
        .then(res => {
            console.log(res);
            if (res.code == 200) {
                // 根据返回结果更新角色的 status 属性
                const updatedRole = data.find(role => role.id === id);
                if (updatedRole) {
                    updatedRole.status = nStatus;
                }
            } else {
                alert_show.value = true
                message.value = res.message
                setTimeout(() => {
                    alert_show.value = false
                }, 1000)

            }
        })
        .catch(errMsg => {
            console.log(errMsg);
        });
};
/**
 * 删除角色信息
 * @param id 角色编号
 */
const deleteRole = (id: number) => {
    const roleIds = (<number[]>[]);
    roleIds.push(id)
    console.log(roleIds)
    removeRole(roleIds)
        .then(res => {
            console.log(res)
            if (res.code == 200) {
                data.splice(data.findIndex(role => role.id == id), 1)
            } else {
                alert_show.value = true
                message.value = res.message
                setTimeout(() => {
                    alert_show.value = false
                }, 1000)
            }
        }).catch(errMsg => {
        console.log(errMsg)
    })
}

const options = ref<SelectProps['options']>([
    {
        value: '1',
        label: '启用',
    },
    {
        value: '0',
        label: '禁用',
    },
])

interface conditionData {
    name?: string
    status?: number
    start_end_time?: string
    code?: string
}

const conditionForm = reactive<conditionData>({
    name: undefined,
    status: undefined,
    start_end_time: '',
    code: undefined
})

/**
 * 条件查询
 * TODO 当数量过大时分页没做好
 * @param values
 */
const finish_condition = (values: any) => {
    console.log("传入的参数", values)
    const condition_data: RoleCondition = {
        name: values.name,
        code: values.code,
        startTime: values.start_end_time[0],
        endTime: values.start_end_time[0],
    }
    if (values.status == undefined) {
        condition_data.status = 1
    } else {
        condition_data.status = values.status.value
    }
    console.log("传入后端的条件参数", condition_data)
    console.log("data:", data)
    console.log('Success:', values);
    // 调用后端接口获取角色信息
    getRoleByCondition(condition_data).then(res => {
        console.log("条件查询角色信息", res)
        const {records, current, size, total} = res.data
        data.splice(0, data.length, ...records);
        pagination.current = current
        pagination.pageSize = size
        pagination.total = total
        console.log(data)
    }).catch(errMsg => {
        console.log(errMsg.message)
    })
};

/**
 * 清空条件表单信息
 */
const clearForm = () => {
    conditionForm.name = ''
    conditionForm.status = undefined
    conditionForm.code = ''
    conditionForm.start_end_time = undefined
}

const modelShow = ref<boolean>(false);

const title = ref()
const add_title = "添加角色"

const update_title = "修改角色信息"
const showModal = () => {
    title.value = add_title
    modelShow.value = true;
};

const updateShowModel = (record: any) => {
    console.log(record)
    // 将record中的值赋值给roleForm
    Object.assign(roleForm, record);
    title.value = update_title
    modelShow.value = true
}

const roleForm = reactive({
    id: undefined,
    name: "",
    code: "",
    status: undefined,
    sort: 0,
    description: "",
    type: undefined,
    createTime: undefined
});

const addOrUpdateMessageShow = ref<boolean>(false)
const addOrUpdateMessage = ref<string>()
const succeed_alert_show = ref<boolean>(false)
const succeed_alert_message = ref<string>()

const onFinish = () => {
    console.log('Success:', roleForm);
    // 当ID为空时表示新增,不为空表示修改
    const saveUpdateRoleData = convert(roleForm);
    if (roleForm.id == undefined) {
        addRole(saveUpdateRoleData)
    } else {
        updateRole(saveUpdateRoleData)
    }

};

/**
 * 更新角色信息
 * @param saveUpdateRoleData
 */
const updateRole = (saveUpdateRoleData: SaveUpdateRoleData) => {
    updateRoleInfo(saveUpdateRoleData).then(res => {
        if (res.data.code == 200) {
            console.log("更新角色信息成功！", res)
            const index = data.findIndex(role => role.id === saveUpdateRoleData.id);
            if (index !== -1) {
                // 使用 $set 方法替换数组中的元素
                data.splice(index, 1, saveUpdateRoleData);
            }
            succeed_alert_show.value = true
            succeed_alert_message.value = res.data.message
            modelShow.value = false
            setTimeout(() => {
                succeed_alert_show.value = false
            }, 1000)

        } else {
            console.log("更新角色信息失败！", res)
            addOrUpdateMessageShow.value = true
            addOrUpdateMessage.value = res.data.message
        }
    }).catch(errMsg => {
        console.log("更新角色信息失败！", errMsg)
    }).finally(() => {
        setTimeout(() => {
            addOrUpdateMessageShow.value = false
        }, 1500)
    })
}
/**
 * 添加角色信息
 * @param saveUpdateRoleData
 */
const addRole = (saveUpdateRoleData: SaveUpdateRoleData) => {
    addRoleInfo(saveUpdateRoleData).then(res => {
        if (res.data.code == 200) {
            console.log("添加角色信息成功！", res)
            succeed_alert_show.value = true
            succeed_alert_message.value = res.data.message
            modelShow.value = false
            setTimeout(() => {
                succeed_alert_show.value = false
            }, 1000)
        } else {
            console.log("添加角色信息失败！", res)
            addOrUpdateMessageShow.value = true
            addOrUpdateMessage.value = res.data.message
        }
    }).catch(errMsg => {
        console.log("添加角色信息失败！", errMsg)
    }).finally(() => {
        setTimeout(() => {
            addOrUpdateMessageShow.value = false
        }, 1500)
    })
}
const convert = (roleForm: any) => {
    const saveUpdateRoleData: SaveUpdateRoleData =
        {
            id: roleForm.id,
            name: roleForm.name,
            code: roleForm.code,
            status: roleForm.status,
            sort: roleForm.sort,
            description: roleForm.description,
            type: roleForm.type,
            createTime: roleForm.createTime
        }
    return saveUpdateRoleData
}
/**
 * 监听 modelShow <br/>
 * 当弹窗关闭时需要清空数据元素
 */
watch(modelShow, (newValue) => {
    if (newValue == false) {
        roleForm.id = undefined
        roleForm.name = ""
        roleForm.code = ""
        roleForm.status = undefined
        roleForm.sort = 0
        roleForm.description = ""
    }
})

/**
 * 导出excel表格
 */
const exportRoleInfos = () => {
    exportRoleInfoAPI()
        .then((resp) => {
            console.log(resp)
            download.excel(resp.data, '角色列表.xls')
        })
        .catch((error) => {
            console.log('下载文件失败:', error);
        });
}

// 以下是角色菜单的分配

const assign_role_menu_model_show = ref<boolean>(false)

const assign_role_form = reactive({
    id: undefined,
    name: "",
    code: "",
    menuIds: []
})
const show_assign_role_model = (record) => {
    assign_role_menu_model_show.value = true
    console.log(record)
    assign_role_form.id = record.id
    assign_role_form.name = record.name
    assign_role_form.code = record.code
    console.log(assign_role_form)
    // 获取当前用户的菜单权限
    getPermissionListRole(assign_role_form.id).then((res) => {
        if (res.data.code == 200) {
            console.log("获取" + assign_role_form.id + "的菜单数据成功", res)
            checkedKeys.value = res.data.data
            console.log(checkedKeys)
        }
    }).catch(errMsg => {
        console.log("获取" + assign_role_form.id + "的菜单数据失败", errMsg)
    })
}

/**
 * 将菜单数据转化为树形格式
 * @param data
 */
const createTree = (data) => {
    const map = {};
    const tree = [];

    // 创建一个映射，以便通过id查找节点
    data.forEach((item) => {
        map[item.id] = {key: item.id, title: item.title, children: []};
    });

    // 构建树结构
    data.forEach((item) => {
        const node = map[item.id];

        if (item.parentId) {
            const parent = map[item.parentId];
            if (parent) {
                parent.children.push(node);
            }
        } else {
            tree.push(node);
        }
    });
    console.log("转化后的树型结构", tree)
    return tree;
}

// 选中菜单
const checkedKeys = ref<string[]>([]);

const menuTree = reactive([])
const getTreeData = () => {
    getSimpleList().then((res) => {
        if (res.data.code == 200) {
            console.log("获取信息成功！", res)
            const menuOldData = res.data.data
            menuTree.push(...createTree(menuOldData))
            console.log("转化后的", menuTree)
        } else {
            console.log("信息获取失败")
        }
    }).catch(errMsg => {
        console.log(errMsg)
    })
}

/***
 * 调用接口赋予角色菜单权限
 */
const assign_role_menu = () => {
    console.log("选中的菜单是：", assign_role_form)
    assignRoleMenu(assign_role_form.menuIds, assign_role_form.id)
        .then(res => {
            if (res.data.code == 200) {
                console.log("赋予角色菜单成功！", res)
                assign_role_menu_model_show.value = false
                succeed_alert_show.value = true
                succeed_alert_message.value = res.data.message
                setTimeout(() => {
                    succeed_alert_show.value = false
                }, 1000)
            } else {
                console.log(res)
            }
        })
        .catch(resMsg => {
            console.log(resMsg)
        })
}

watch(checkedKeys, () => {
    console.log('checkedKeys', checkedKeys);
    assign_role_form.menuIds = checkedKeys.value
});

</script>

<style scoped>

</style>
