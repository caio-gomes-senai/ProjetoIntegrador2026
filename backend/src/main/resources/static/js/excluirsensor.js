document.querySelectorAll('.btn-excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        if (confirm('Confirma a exclusão do sensor?')) {
            const row = this.closest('tr');
            const sensorId = this.dataset.sensorId;
            console.log('sensorId = ' + sensorId);

            fetch(`/post-sensores/${sensorId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                    console.log('Sensor excluído com sucesso.');
                    row.remove();
                } else {
                    console.error('Erro ao excluir sensor.');
                    alert('Erro ao excluir sensor.');
                }
            })
            .catch(error => {
                console.error('Erro de rede:', error);
                alert('Erro de conexão com o servidor.');
            });
        }
    });
});
