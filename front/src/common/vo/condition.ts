export interface Condition {
    current?: number
    size?: number
}

export function newCondition(): Condition {
    return {
        current: 1,
        size: 10
    }
}
