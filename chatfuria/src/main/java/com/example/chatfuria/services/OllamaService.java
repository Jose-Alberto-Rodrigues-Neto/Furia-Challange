package com.example.chatfuria.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OllamaService {

    private final String ollamaUrl = "http://ollama:11434/api/generate";

    public String askOllama(String question) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

       String furiaInfo = "Use como contexto para minha pergunta: FURIA Esports - Informacoes completas sobre o time de Counter-Strike 2. Historia da Organizacao: Fundada em 2017 no Brasil por Andre Akkari e Jaime 'raizen' Padua. Comecou como uma organizacao de CS:GO e expandiu para outras modalidades com o tempo. Conquistou rapido destaque pela abordagem agressiva e estilo de jogo ousado. Base da equipe movida para os EUA para disputar torneios internacionais com mais frequencia. Elenco Atual (abril/2025): Andrei 'arT' Piovezan (In-Game Leader), Yuri 'yuurih' Santos, Kaike 'KSCERATO' Cerato, Gabriel 'FalleN' Toledo (AWPer e veterano), Marcelo 'chelo' Cespedes. Jogadores Notaveis Anteriores: Henrique 'HEN1' Teles, Vinicius 'VINI' Figueiredo, Rafael 'saffee' Costa, Nicholas 'guerri' Nogueira (ex-jogador e atual coach). Staff Tecnico: Nicholas 'guerri' Nogueira - Coach, Matheus 'kaov' Moraes - Performance Coach, Andre Akkari - Co-fundador e gestor. Conquistas Relevantes: ESL Pro League Season 12 - North America (Campeao), DreamHack Open Summer 2020 NA (Campeao), CBCS Elite League 2021 Season 1 (Campeao), Top 4 no PGL Major Antwerp 2022, Finalista da IEM Dallas 2023. Desempenho Recente: 2024: Temporada irregular, mas com bons desempenhos no IEM Chengdu e BLAST Premier. Atual ranking HLTV: Top 15 mundial. Proximos Jogos (maio-junho 2025): IEM Dallas 2025 - Comeca em 27 de maio, ESL Challenger Melbourne 2025 - 21 de junho, BLAST Premier Fall Groups 2025 - Previsao para final de junho. Recordes e Curiosidades: arT e conhecido por ser um dos IGLs mais agressivos do mundo, famoso por 'rushar' em rounds decisivos. yuurih e KSCERATO figuram constantemente entre os melhores jogadores brasileiros no HLTV. FURIA e a equipe brasileira com mais tempo consecutivo entre o top 10 do ranking HLTV entre 2019 e 2022. Rivalidades classicas contra MIBR e Team Liquid. Maiores Vitorias: Vitoria historica sobre Astralis na DreamHack Masters Spring 2020. Campanha memoravel no PGL Major Antwerp 2022 chegando as semifinais. Maiores Derrotas: Eliminacao dolorosa para Heroic no IEM Rio Major 2022 nas semifinais em casa. Links Oficiais: Site oficial: https://www.furia.gg, Twitter: https://twitter.com/FURIA, HLTV Team Page: https://www.hltv.org/team/8297/furia. Ultima Atualizacao: Abril de 2025.";

        String prompt = furiaInfo + question;

        String jsonBody = String.format(
                "{\"model\":\"llama2\", \"prompt\":\"%s\"}", prompt
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ollamaUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Versão síncrona (bloqueante)
        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );
       
        JSONObject jsonResponse = new JSONObject(response.body());
        String formattedResponse = jsonResponse.getString("response");

        
        formattedResponse = formattedResponse.replace("\\n", "\n").trim();

        return formattedResponse;
    }
    public String askFallen(String question) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

       
        String fallenInfo = "Responda minhas perguntas como se você fosse: Gabriel 'FalleN' Toledo e um dos jogadores mais icônicos da história do Counter-Strike brasileiro e mundial. Nascido em 30 de maio de 1991 em Ituverava, Sao Paulo, FalleN se destacou inicialmente no CS 1.6 antes de se tornar um dos principais nomes do CS:GO. Conhecido como 'O Professor', FalleN ganhou respeito global por sua inteligencia tática, habilidades de AWP e capacidade de lideranca dentro e fora dos servidores. E um dos poucos jogadores que conseguiram equilibrar as funções de In-Game Leader e AWPer ao mesmo tempo, papel que exige alto nível de concentracao e tomada de decisão. Como pessoa, FalleN e admirado por sua humildade, comunicacao clara, pensamento estrategico e paixão em formar novas gerações de jogadores. Empresário e empreendedor, também fundou a Fallen Store e academias de esports para treinar jovens talentos. Ele e visto como um mentor, sempre buscando o crescimento coletivo de seus times. Fora do jogo, FalleN é fã de música, tecnologia e educacao, reforcando seu perfil de pessoa dedicada, estudiosa e que valoriza o trabalho em equipe. Atualmente, atua pela FURIA Esports, trazendo sua vasta experiência e lideranca para guiar a equipe em campeonatos internacionais.";

        String prompt = fallenInfo + question;

        String jsonBody = String.format(
                "{\"model\":\"llama2\", \"prompt\":\"%s\"}", prompt
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ollamaUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Versão síncrona (bloqueante)
        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString()
        );

       
        JSONObject jsonResponse = new JSONObject(response.body());
        String formattedResponse = jsonResponse.getString("response");

        
        formattedResponse = formattedResponse.replace("\\n", "\n").trim();

        return formattedResponse;

       }
}