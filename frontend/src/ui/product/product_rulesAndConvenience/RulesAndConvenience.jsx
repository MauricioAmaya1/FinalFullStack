import styles from './rulesAndConvenience.module.css'

import { textToArray } from '../../../functions/textToArray'

const RulesAndConvenience = ({ dataHouseRules, dataHealthAndSecurity, dataCancelPolitics }) => {

    const houseRulesSorted = textToArray(dataHouseRules)
    const healthAndSecuritySorted = textToArray(dataHealthAndSecurity)
    const cancelPoliticsSorted = textToArray(dataCancelPolitics)

    return (
        <div className={styles.rulesAndConvenience}>
            <div className={styles.title}>
                <div className='container'>
                    <div className='row'>
                        <h2 className={styles.content}>Qué tenés que saber</h2>
                    </div>
                </div>
            </div>
            <div className='container'>
                <div className='row'>
                    <ul className={`${styles.rulesList} ${styles.content}`}>
                        <ul className={styles.subList}>
                            <h3 className={styles.subtitle}>Normas de la casa</h3>
                            {houseRulesSorted?.map((rule, id) => <li className={styles.listItem} key={id}>{rule}</li>)}
                        </ul>
                        <ul className={styles.subList}>
                            <h3 className={styles.subtitle}>Salud y seguridad</h3>
                            {healthAndSecuritySorted?.map((rule, id) => <li className={styles.listItem} key={id}>{rule}</li>)}
                        </ul>
                        <ul className={styles.subList}>
                            <h3 className={styles.subtitle}>Política de cancelación</h3>
                            {cancelPoliticsSorted?.map((rule, id) => <li className={styles.listItem} key={id}>{rule}</li>)}
                        </ul>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default RulesAndConvenience