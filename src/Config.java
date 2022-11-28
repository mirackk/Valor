import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// read the txt files
public class Config {
    // for heroes
    public List<List<String>> warriorsInfo;
    public List<List<String>> sorcerersInfo;
    public List<List<String>> paladinsInfo;

    // for monsters
    public List<List<String>> dragonsInfo;
    public List<List<String>> exoskeletonsInfo;
    public List<List<String>> spiritsInfo;

    // for items
    public List<List<String>> weaponryInfo;
    public List<List<String>> armoryInfo;
    public List<List<String>> potionsInfo;

    public List<List<String>> fireSpellsInfo;
    public List<List<String>> iceSpellsInfo;
    public List<List<String>> lightningSpellsInfo;

    public final String configDir = "configs/";

    // read a single fine and put int List<List<String>>
    public List<List<String>> readFile(String path) {
        List<List<String>> res = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line = null;
            while ((line = br.readLine()) != null) {
                if ("".equals(line))//remove empty line
                    continue;
                List<String> tmp = new ArrayList<>();
                String[] str = line.split("\\s+");
                for (String s : str) {
                    tmp.add(s);
                }
                res.add(tmp);
                //System.out.println(tmp);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("read file fail, try again");
        }
        return res;
    }

    public Config() {
    }

    public void getCondfigs() {
        warriorsInfo = readFile(configDir + "warriors.txt");
        sorcerersInfo = readFile(configDir + "Sorcerers.txt");
        paladinsInfo = readFile(configDir + "Paladins.txt");

        dragonsInfo = readFile(configDir + "Dragons.txt");
        exoskeletonsInfo = readFile(configDir + "Exoskeletons.txt");
        spiritsInfo = readFile(configDir + "Spirits.txt");

        weaponryInfo = readFile(configDir + "Weaponry.txt");
        armoryInfo = readFile(configDir + "Armory.txt");
        potionsInfo = readFile(configDir + "Potions.txt");

        fireSpellsInfo = readFile(configDir + "FireSpells.txt");
        iceSpellsInfo = readFile(configDir + "IceSpells.txt");
        lightningSpellsInfo = readFile(configDir + "LightningSpells.txt");
    }
}
