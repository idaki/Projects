import React, { useState } from 'react';
import Modal from 'react-modal';
import CreateTournamentModal from '../tournaments/tournament-create/CreateTournamentModal';

Modal.setAppElement('#root');

export default function HeaderModal() {
    const [isOpen, setIsOpen] = useState(false);

    const openModal = () => {
        setIsOpen(true);
    };

    const closeModal = () => {
        setIsOpen(false);
    }

    return (
        <header className="bg-dark py-5">
            <div className="container px-4 px-lg-5 my-5">
                <div className="text-center text-white">
                    <h1 className="display-4 fw-bolder">SerdicaGrid</h1>
                    <p className="lead fw-normal text-white-50 mb-0">Where every pixel sparks a possibility. Join the community for epic online tournaments!</p>
                </div>
            </div>
          
        </header>
    );
}
