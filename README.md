# S-DES 加密算法用户指南

## 1.介绍

S-DES（Simplified Data Encryption Standard）是一种简化的数据加密标准，通常用于教育和学术目的。本用户指南将向您介绍如何使用 S-DES 算法来加密和解密文本数据。

### 1.1 主要功能

- **加密**：将明文转换为密文，使用密钥进行加密。
- **解密**：将密文转换回明文，使用相同的密钥进行解密。

## 2.安装和使用

### 2.1 下载和导入代码

1. 下载地址：[S-DES代码仓库](https://github.com/MoonlightAFar/S-DES)
2. 运行环境：JDK1.8
3. 操作步骤：您可以从https://github.com/MoonlightAFar/S-DES 中下载 S-DES 算法的 Java 代码。然后，将代码导入您喜欢的 Java 集成开发环境（IDE）中。

### 2.2 可视化界面说明

以下图片为软件可视化界面操作的功能说明：
![image](file://C:\Users\asus\Desktop\S-DES\images\可视化界面.png?msec=1728233281311)

### 2.2 密钥生成

在开始使用 S-DES 进行加密和解密之前，您需要准备一个 10 位的二进制密钥。这个密钥将用于加密和解密过程。

### 2.3 加密和解密操作及示例

以下是如何使用 S-DES 进行加密和解密的步骤：

注意：

- 输入信息规格为：8位二进制字符串或者ASCII字符码
- 密钥规格为：10位二进制字符串

#### 2.3.1 加密

##### 2.3.1.1 操作

- 选择输入信息的格式
- 在加密信息栏输入明文信息（8位二进制或者ASCII字符串）
- 在密钥栏输入10位的二进制密钥
- 点击加密按钮
- 在输出栏中获取到加密后的信息

##### 2.3.1.2 示例

以下图片为加密操作的示例图片：
![loadingag11234](file://C:\Users\asus\Desktop\S-DES\images\加密操作.png?msec=1728233427538)

#### 2.3.2 解密

##### 2.3.2.1 操作

- 选择输入信息的格式
- 在解密信息栏输入密文信息（从加密机中获取的加密输出密文）
- 在密钥栏输入与加密操作相同10位的二进制密钥
- 点击解密按钮
- 在输出栏中获取到解密后的信息

##### 2.3.2.2 示例

以下图片为解密操作的示例图片：
![loadingag11235](file://C:\Users\asus\Desktop\S-DES\images\解密操作.png?msec=1728233566385)

## 3.注意事项

1. 进行操作前请首先选择输入信息格式，否则本软件将会弹出提示框进行提醒
2. 请确保输入的内容与要求的格式相符
   - 输入信息规格为：8位二进制字符串或者ASCII字符码
   - 密钥规格为：10位二进制字符串
3. 进行解密操作时，请输入由本软件加密后的密文，否则可能会得到乱码的解密信息
4. 解密操作时需要使用与加密操作相同的密钥，否则将无法得到原明文

## 4.常见问题解答

### 4.1 加密和解密的区别是什么？

- 加密是将明文转换为密文的过程，使用密钥进行操作。
- 解密是将密文转换回明文的过程，需要相同的密钥。

### 4.2 如何生成 10 位的二进制密钥？

- 您可以手动创建一个 10 位的二进制字符串，或使用随机生成的二进制密钥。

### 4.3 S-DES 安全吗？

- S-DES 是一个非常简化的加密算法，不适合用于真正的安全性要求。
- 不要在生产环境中使用 S-DES，因为它的安全性较低。

## **开发手册 - S-DES 加密算法**

## 1.代码结构

S-DES加密算法的代码由以下几个主要部分组成：

### 1.1. `IP` 类和`SonKeys`类

- `IP` 类和`Sonkeys` 类包含了S-DES算法中的初始置换（IP）和密钥生成相关的函数。

### 1.2. `RoundF` 类

- `RoundF` 类实现了S-DES算法的轮函数，包括拓展（EP）、S-盒、SP置换、异或运算等。

### 1.3. `SDES` 类

- `SDES` 类包含一个`S_DES`方法，用于调用其他类的函数以执行完整的加密或解密过程。

### 1.4. `SDES_ASCII` 类

- `SDES_ASCII` 类是一个实用类，用于将ASCII文本与S-DES加密算法集成。

### 1.5.`GUI`类

- GUI`类是一个基于JavaSwing的一个GUI可视化窗口类，提供用户可视化操作

## 2.依赖项

S-DES加密算法的代码没有依赖于外部库或工具，可以直接在Java环境中运行。

## 3.主要类和方法

### 3.1. `IP` 类和`SonKeys`类

#### 3.1.1成员变量

##### `IP[]`

- **描述**：IP置换盒

##### `IP_inverse[]`

- **描述**：最终IP置换盒

##### `P_10[]`

- **描述**：$P_{10}$置换盒

##### `P_8[]`

- **描述**：$P_{8}$置换盒

##### `Leftshift1[]``Leftshift2[]`

- **描述**：左移盒

#### 3.1.2主要方法

##### `IP(String plaintext)`

- **描述**：对8位明文进行初始置换。

- **参数**：
  
  - `plaintext` (String): 8位明文。

- **返回值**：
  
  - `String`: 经过初始置换后的8位文本。

##### `IIP_inverse(String lasttext)`

- **描述**：对8位明文进行初始置换。

- **返回值**：
  
  - `String`: 经过最终置换后的8位文本。
    
    ##### `Key(String key_original)`

- **描述**：生成子密钥k1和k2。

- **参数**：
  
  - `key_original` (String): 10位原始密钥。

- **返回值**：
  
  - `String[]`: 包含两个子密钥的字符串数组。

### 3.2. `RoundF` 类

#### `RoundFun(int[] round_data, int[] round_key)`

- **描述**：轮函数，用于S-DES的加密和解密。

- **参数**：
  
  - `round_data` (int[]): 8位轮数据。
  - `round_key` (int[]): 8位轮密钥。

- **返回值**：
  
  - `int[]`: 加密或解密后的8位数据。

### 3.3. `SDES` 类

#### `S_DES(String Plain_Text, String Key, int state)`

- **描述**：执行S-DES加密或解密的主要函数。

- **参数**：
  
  - `Plain_Text` (String): 明文或密文。
  - `Key` (String): 10位二进制密钥。
  - `state` (int): 0表示加密，1表示解密。

- **返回值**：
  
  - `String[]`: 包含加密后的密文和原始密钥的字符串数组。

### 3.4. `SDES_ASCII` 类

#### `transform(String Input_Text, String Key, int state)`

- **描述**：将ASCII文本与S-DES加密算法集成，支持加密和解密操作。

- **参数**：
  
  - `Input_Text` (String): 要加密或解密的ASCII文本。
  - `Key` (String): 10位二进制密钥。
  - `state` (int): 0表示加密，1表示解密。

- **返回值**：
  
  - `String`: 加密后的密文或解密后的明文。

## 4.示例用法

### 4.1. 加密示例

以下示例演示如何使用S-DES加密算法对明文进行加密：

```java
//二进制加密
String plainText = "10110110";
String key = "1010001101";
String[] results = SDES.S_DES(plainText, key, 0);
String cipherText = results[0];
System.out.println("Cipher Text: " + cipherText);
```

```java
//ASCII码加密
String plainText = "Hello, World!";  
String key = "1010001101";  
String result = SDES_ASCII.transform(plainText, key, 0);  
String cipherText = result;  
System.out.println("Cipher Text: " + cipherText);
```

### 4.2. 解密示例

以下示例演示如何使用S-DES加密算法对密文进行解密：

```java
String cipherText = "1010101010";
String key = "1010001101";
String[] results = SDES.S_DES(cipherText, key, 1);
String decryptedText = results[0];
System.out.println("Decrypted Text: " + decryptedText);
```

```java
//ASCII码加密
String cipherText = "ÂYYÝ­4ðÝÈYá";  
String key = "1010001101";  
String result = SDES_ASCII.transform(cipherText, key, 1);  
String decryptedText = result;  
System.out.println("Cipher Text: " + decryptedText);
```

## 5.安全性

S-DES是一个基于单一密钥的对称加密算法，但它

- S-DES是一个基于单一密钥的对称加密算法

- S-DES的密钥空间只有10-bit，仅1024种情况，不适用于现代安全要求较高的应用程序

- S-DES中的置换盒，替换盒，轮函数和移位都展现了密码学的精华，即扩散和混淆，通过这一系列的变化能够很好地抹去明文和密文的统计特征、密文和密钥的统计关系

## 6.扩展和定制

- 根据需要来修改`IP` 类、`SonKeys`类和`RoundF`类中的置换盒

- 可以考虑使用更高效的算法或数据结构来实现置换、S 盒替换等操作，以提高加密和解密的速度。

- 对于暴力破解功能，可以考虑使用更优化的搜索算法，以减少破解时间

- 将S-DES算法集成到其他应用程序中，以实现数据保护的目标

## **7.特别鸣谢**

- 课程名称：信息安全导论
- 教学班级：992987-002
- 任课教师：向宏
- 单位：重庆大学大数据与软件学院
- 小组：数据三剑客
- 若有任何疑问或建议，请联系开发团队：[1401438790@qq.com](mailto:1401438790@qq.com) 

## **8.附录**

#### 1、术语表：

- S-DES：简化数据加密标准，一种对称密钥加密算法

- 置换：对数据进行重新排列的操作

- 左移：将二进制数据向左移动一定位数的操作

- S 盒替换：使用 S 盒（查找表）对数据进行替换的操作

- 密钥：用于加密和解密数据的秘密值

- 明文：未加密的数据

- 密文：加密后的数据

#### 2、参考资料：

- 《信息安全导论》

- Python 官方文档

- Vue 官方文档。

- Element UI 官方文档

- Flask 官方文档
