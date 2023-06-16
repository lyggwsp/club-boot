import http from "@/store/http";

const BASE_URL = "http://localhost:8091"

export async function getRoleCondition(current: number, size: number) {
    return http.request(`${BASE_URL}/sys-role/condition`,
        "post",
        {current: current, size: size}
    )
}

