class LoginPage {
    constructor(page) {
        this.page = page;
        this.usernameInput = '#loginusername';
        this.passwordInput = '#loginpassword';
        this.loginBtn = 'button:has-text("Log in")';
    }

    async login(username, password) {
        await this.page.fill(this.usernameInput, username);
        await this.page.fill(this.passwordInput, password);
        await this.page.click(this.loginBtn);
    }
}

module.exports = { LoginPage };
