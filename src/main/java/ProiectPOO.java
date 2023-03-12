import java.io.*;
import java.util.ArrayList;

public class ProiectPOO {

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
        }
        else {
            Sistem sistem = Sistem.getInstance();
            String pathStreamers = "src/main/resources/" + args[0];
            String pathStreams = "src/main/resources/" + args[1];
            String pathUsers = "src/main/resources/" + args[2];
            String pathCommands = "src/main/resources/" + args[3];
//            String pathCommands = "src/main/resources/test6/commands.txt";
//            String pathStreamers = "src/main/resources/inputs1/streamers.csv";
//            String pathStreams = "src/main/resources/inputs1/streams.csv";
//            String pathUsers = "src/main/resources/inputs1/users.csv";
            citire(sistem, pathStreamers, pathStreams, pathUsers);

            try (BufferedReader br = new BufferedReader(new FileReader(pathCommands))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(" ");
                    if (words[1].equalsIgnoreCase("list")) {
                        int id = Integer.parseInt(words[0]);
                        if (sistem.getUserById(id) != null) {
                            CommandListByUser command = new CommandListByUser(id);
                            command.execute(sistem);
                        }
                        if (sistem.getStreamerById(id) != null) {
                            CommandListByStreamer command = new CommandListByStreamer(id);
                            command.execute(sistem);
                        }
                    } else if (words[1].equalsIgnoreCase("add")) {
                        String name = "";
                        for (int i = 6; i < words.length; i++) {
                            name += words[i] + " ";
                        }
                        name = name.substring(0, name.length() - 1);
                        CommandAdd command = new CommandAdd(Integer.parseInt(words[0]), Integer.parseInt(words[2]), Integer.parseInt(words[3]), Integer.parseInt(words[4]), Long.parseLong(words[5]), name);
                        command.execute(sistem);
                    } else if (words[1].equalsIgnoreCase("delete")) {
                        int streamerId = Integer.parseInt(words[0]);
                        int streamId = Integer.parseInt(words[2]);
                        CommandDelete command = new CommandDelete(streamerId, streamId);
                        command.execute(sistem);
                    } else if (words[1].equalsIgnoreCase("listen")) {
                        int userId = Integer.parseInt(words[0]);
                        int streamId = Integer.parseInt(words[2]);
                        CommandListen command = new CommandListen(userId, streamId);
                        command.execute(sistem);
                    } else if (words[1].equalsIgnoreCase("recommend")) {
                        int userId = Integer.parseInt(words[0]);
                        if (words[2].equalsIgnoreCase("song")) {
                            CommandRecommend command = new CommandRecommend(userId, 1);
                            command.execute(sistem);
                        } else if (words[2].equalsIgnoreCase("podcast")) {
                            CommandRecommend command = new CommandRecommend(userId, 2);
                            command.execute(sistem);
                        } else if (words[2].equalsIgnoreCase("audiobook")) {
                            CommandRecommend command = new CommandRecommend(userId, 3);
                            command.execute(sistem);
                        }
                    } else if (words[1].equalsIgnoreCase("surprise")) {
                        int userId = Integer.parseInt(words[0]);
                        if (words[2].equalsIgnoreCase("song")) {
                            CommandSurprise command = new CommandSurprise(userId, 1);
                            command.execute(sistem);
                        } else if (words[2].equalsIgnoreCase("podcast")) {
                            CommandSurprise command = new CommandSurprise(userId, 2);
                            command.execute(sistem);
                        } else if (words[2].equalsIgnoreCase("audiobook")) {
                            CommandSurprise command = new CommandSurprise(userId, 3);
                            command.execute(sistem);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sistem.getUsers().clear();
            sistem.getStreamers().clear();
            sistem.getStreams().clear();
        }

    }


    public static void citire(Sistem sistem, String pathStreamers, String pathStreams, String pathUsers) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathStreamers))) {
            String line;
            int page = 0;
            while ((line = br.readLine()) != null) {
                if (page > 0) {
                    String[] words = line.split(",");
                    sistem.getStreamers().add(Sistem.createStreamer(Integer.parseInt(words[0].substring(1,words[0].length()-1)), Integer.parseInt(words[1].substring(1,words[1].length()-1)), words[2].substring(1,words[2].length()-1)));
                }
                page++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(pathUsers))) {
            String line;
            int page = 0;
            while ((line = br.readLine()) != null) {
                if (page > 0) {
                    String[] words = line.split(",");
                    User user = new User(words[1].substring(1,words[1].length()-1), Integer.parseInt(words[0].substring(1,words[0].length()-1)));
                    String streamsListString = words[2].substring(1,words[2].length()-1);
                    String[] streams = streamsListString.split(" ");
                    ArrayList<Integer> streamsList = new ArrayList<Integer>();
                    for (int i = 0; i < streams.length; i++) {
                        streamsList.add(Integer.parseInt(streams[i]));
                    }
                    user.setStreams(streamsList);
                    sistem.getUsers().add(user);
                }
                page++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(pathStreams))) {
            String line;
            int page = 0;
            while ((line = br.readLine()) != null) {
                if (page > 0) {
                    String[] words = line.split(",");
                    String name = "";
                    for (int i = 7; i < words.length; i++) {
                        name += words[i] + ",";
                    }
                    name = name.substring(1, name.length() - 2);
                    Stream stream = new Stream(Integer.parseInt(words[0].substring(1,words[0].length()-1)), Integer.parseInt(words[1].substring(1,words[1].length()-1)), Integer.parseInt(words[2].substring(1,words[2].length()-1)), Long.parseLong(words[3].substring(1,words[3].length()-1)), Integer.parseInt(words[4].substring(1, words[4].length()-1)), Long.parseLong(words[5].substring(1,words[5].length()-1)), Long.parseLong(words[6].substring(1,words[6].length()-1)), name);
                    sistem.getStreams().add(stream);
                }
                page++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
