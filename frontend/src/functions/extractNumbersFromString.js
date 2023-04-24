export function extractNumbersFromString(text) {
    const onlyNumbers = text.replace(/\D/g, "")
    return onlyNumbers
}