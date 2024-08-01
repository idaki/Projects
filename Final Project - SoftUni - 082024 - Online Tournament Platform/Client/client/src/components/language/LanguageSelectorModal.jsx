import React from 'react';
import { useTranslation } from 'react-i18next';

export default function LanguageSelectorModal() {
    const { i18n } = useTranslation();

    const changeLanguage = (lng) => {
        i18n.changeLanguage(lng);
    };

    return (
        <div className="language-selector-modal">
            <select  className="btn btn-outline-light mt-auto"onChange={(e) => changeLanguage(e.target.value)} defaultValue={i18n.language}>
                <option value="en">English</option>
                <option value="nl">Nederlands</option>
            </select>
        </div>
    );
}
