import http from "@/store/http";
import axios from "axios";


const BASE_URL = "http://localhost:8091"

/**
 * 分页查询获取角色信息记录条数
 * @param current 当前第几页
 * @param size 页面大小
 */
export async function getRoleCondition(current: number, size: number) {
    return http.request(`${BASE_URL}/sys-role/condition`,
        "post",
        {current: current, size: size}
    )
}

/**
 * 更新角色状态
 * @param id 角色ID
 * @param status 角色状态
 */
export async function checkRoleStatus(id: number, status: boolean) {
    return http.request(`${BASE_URL}/sys-role/update-status?id=${id}&disabled=${status}`,
        "patch",
    )
}

/**
 * 删除角色信息
 * @param params
 */
export async function removeRole(params: number[]) {
    return http.request(`${BASE_URL}/sys-role/delete`,
        'delete', {},
        {
            headers: {
                "Content-Type": "application/json",
                "X-Role-Ids": params
            }
        }
    )
}

export interface RoleCondition {
    name?: string
    status?: number
    startTime?: string
    endTime
    code?: string
}

/**
 *  根据 条件对象获取角色信息记录
 * @param data
 */
export async function getRoleByCondition(data: RoleCondition) {
    return http.request(`${BASE_URL}/sys-role/condition`,
        "post",
        data
    )
}


export interface SaveUpdateRoleData {
    code?: string;
    description?: string;
    id?: number;
    name?: string;
    sort?: number;
    status?: number;
    type?: number;
    createTime?: string
}

/**
 * 添加角色信息
 * @param roleData 待添加的角色对象
 */
export async function addRoleInfo(roleData: SaveUpdateRoleData) {
    return axios({
        url: `${BASE_URL}/sys-role/save`,
        method: "POST",
        data: roleData
    })
}

/**
 * 更新角色信息
 * @param roleData 待添加的角色对象
 */
export async function updateRoleInfo(roleData: SaveUpdateRoleData) {
    return axios({
        url: `${BASE_URL}/sys-role/update`,
        method: "put",
        data: roleData
    })
}


/**
 * 导出角色信息
 */
export async function exportRoleInfoAPI() {
    return axios({
        url: `${BASE_URL}/sys-role/export-role`,
        method: "get",
        responseType: 'blob',
    })
}

/**
 * 获取精简菜单列表
 */
export async function getSimpleList() {
    return axios({
        url: `${BASE_URL}/sys-menu/list-all-simple`,
        method: "get",
    })
}

/**
 * 获取某个角色的菜单权限
 */
export async function getPermissionListRole(id: number) {
    return axios({
        url: `${BASE_URL}/system/permission/list-role-resources/${id}`,
        method: "get",
    })
}

/**
 * 赋予某个角色菜单权限
 * @param menuIds
 * @param roleId
 */
export async function assignRoleMenu(menuIds: number[], roleId: number) {
    return axios({
        url: `${BASE_URL}/system/permission/assign-role-menu`,
        method: "post",
        data: {menuIds, roleId}
    })
}

