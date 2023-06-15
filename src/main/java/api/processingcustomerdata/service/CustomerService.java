package api.processingcustomerdata.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public interface CustomerService {
    private List<User> users;

    @PostConstruct
    public void loadData() {
        // Faça a leitura dos dados dos arquivos CSV e JSON e armazene na lista 'users'
        // Você pode usar bibliotecas como OpenCSV ou Jackson para realizar a leitura
    }

    public List<User> getEligibleUsers(String region, String classification, int pageNumber, int pageSize) {
        // Filtrar os usuários elegíveis com base na região e classificação
        // Aplicar a lógica de paginação e retornar a lista de usuários elegíveis

        // Exemplo de implementação simplificada:
        List<User> eligibleUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getLocationOutPutDto().getRegion().equalsIgnoreCase(region) && user.getxType().equalsIgnoreCase(classification)) {
                eligibleUsers.add(user);
            }
        }

        int totalCount = eligibleUsers.size();
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalCount);

        List<User> paginatedUsers = eligibleUsers.subList(startIndex, endIndex);

        return paginatedUsers;
    }
}