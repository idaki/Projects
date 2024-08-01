import React, { useState } from 'react';
import Modal from 'react-modal';

import { useTranslation } from 'react-i18next'; // Import useTranslation hook

Modal.setAppElement('#root');

export default function HeaderModal() {
    const { t } = useTranslation(); // Use the useTranslation hook
    const [isOpen, setIsOpen] = useState(false);

    const openModal = () => {
        setIsOpen(true);
    };

    const closeModal = () => {
        setIsOpen(false);
    };

    return (
        <header className="bg-dark py-5">
            <div className="container px-4 px-lg-5 my-5">
                <div className="text-center text-white">
                    <h1 className="display-4 fw-bolder">{t('header.title')}</h1>
                    <p className="lead fw-normal text-white-50 mb-0">{t('header.subtitle')}</p>
                </div>
            </div>
        </header>
    );
}