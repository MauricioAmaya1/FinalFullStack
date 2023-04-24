export function textToArray(text) {

    let description = text
    let newDescription = []
    let paragraph

    let i = 0
    while (i < 1) {

        paragraph = description.slice(0, description.indexOf(".") + 1)
        description = description.slice(paragraph.length, description.length)
        newDescription.push(paragraph)

        if (description.indexOf(".") === -1) {
            i++
        }
    }
    return newDescription
}